package com.lezhai365.mp.controller.propertyservice;

import com.lezhai365.common.exception.TranscationalException;
import com.lezhai365.common.model.CacheUser;
import com.lezhai365.common.model.Page;
import com.lezhai365.mp.controller.BaseController;
import com.lezhai365.pms.model.ComplaintInfo;
import com.lezhai365.pms.model.FaultInfo;
import com.lezhai365.pms.spi.material.IHouseService;
import com.lezhai365.pms.spi.system.IPmsParameterService;
import com.lezhai365.pms.spi.waste.IWasteIntegralService;
import com.lezhai365.pms.spi.wechat.IPropertyServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wap.controller
 * @link :  http://lezhai365.com
 * @created on   :  15-4-20
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value = "/{signinName}/service")
public class PropertyServiceController extends BaseController {
    @Autowired
    IPropertyServiceService propertyServiceService;
    @Autowired
    IWasteIntegralService wasteIntegralService;
    @Autowired
    IPmsParameterService pmsParameterService;
    @Autowired
    IHouseService houseService;

    //账单查询
    @RequestMapping(value = "/billslist")
    public ModelAndView getBillsList(
            HttpServletResponse response,
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);

        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Object houseIdObj = userWxMap.get("defaultHouseId");
        if(null != houseIdObj){
            Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
            Page<Map<String, Object>> billsPage = propertyServiceService.queryPayBillsListByHouseInfoId(houseInfoId, estateId, pageIndex, pageSize);
            List<Map<String, Object>> billsList = billsPage.getContent();

            /**
             * {
             *   "2015-02":[{},{}]
             *   "2015-03":[{},{}]
             *   "2015-04":[{},{}]
             *   "2015-05":[{},{}]
             * }
             */
            Map<String, List<Object>> billsMap = new HashMap<>();
            for (Map<String, Object> map : billsList) {
                String key = (String) map.get("month");
                if (billsMap.containsKey(key)) {
                    List value = billsMap.get(key);
                    value.add(map);
                } else {
                    List value = new ArrayList<Object>();
                    value.add(map);
                    billsMap.put(key, value);
                }
            }

            Map<String, Object> sumNotPayMap = propertyServiceService.querySumNotPayBills(houseInfoId, estateId);

            mv.addObject("sumNotPayMap", sumNotPayMap);
            mv.addObject("billsMap", billsMap);
            mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
            mv.setViewName("propertyservice/paybills");
        } else {
            mv.setViewName("no-default-house");
        }
        return mv;
    }


    //报修查询
    @RequestMapping(value = "/faultlist")
    public ModelAndView getFaultInfoList(
            HttpServletResponse response,
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);

        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");

        Object houseIdObj = userWxMap.get("defaultHouseId");
        if(null != houseIdObj){

            Long houseInfoId = (Long) userWxMap.get("defaultHouseId");

            Page<Map<String, Object>> faultInfoList = propertyServiceService.queryFaultsByHouseInfoId(houseInfoId, estateId, pageIndex, pageSize);
            mv.addObject("faultInfoList", faultInfoList);
            mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
            mv.setViewName("propertyservice/faults");
        } else {
            mv.setViewName("no-default-house");
        }
        return mv;
    }

    @RequestMapping(value = "/faultview")
    public ModelAndView faultView(
            @RequestParam String openid,
            @PathVariable String signinName) {
        ModelAndView mv = new ModelAndView();
        String repairFaultType = "repair_fault_type";
        String repairEmergencyType = "repair_emergency_type";
        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
        Long pmcId = (Long) userWxMap.get("pmcId");
        //从session中获取物业公司的id
        Map<String, Object> repairFaultValue = null;
        Map<String, Object> repairEmergencyValue = null;
        //当前小区id
        repairFaultValue = pmsParameterService.queryInitParamsValue(repairFaultType, pmcId, estateId);
        repairEmergencyValue = pmsParameterService.queryInitParamsValue(repairEmergencyType, pmcId, estateId);
        mv.addObject("repairFaultValue", repairFaultValue);
        mv.addObject("repairEmergencyValue", repairEmergencyValue);
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);
        mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
        mv.setViewName("propertyservice/faults_add");
        return mv;
    }

    //新增报修
    @RequestMapping(value = "/do/addfault", method = RequestMethod.POST)
    public ModelAndView addFaultInfo(
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "imgs[]", required = false) String[] imgs,
            @ModelAttribute FaultInfo faultInfo) throws TranscationalException {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
        Long pmcId = (Long) userWxMap.get("pmcId");
        faultInfo.setHousingEstateId(estateId);
        faultInfo.setHouseInfoId(houseInfoId);

        int flag = propertyServiceService.addFaultInfo(faultInfo,imgs);
//        if (flag > 0) {
            mv.setViewName("redirect:/" + signinName + "/service/faultlist");
//        }
        mv.addObject("openid", openid);
        mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
        return mv;
    }

    //投诉查询
    @RequestMapping(value = "/complaintlist")
    public ModelAndView getComplaintsList(
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);

        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");

        Object houseIdObj = userWxMap.get("defaultHouseId");
        if(null != houseIdObj){
            Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
            Page<Map<String, Object>> complaintList = propertyServiceService.queryComplaintByHouseInfoId(houseInfoId, estateId, pageIndex, pageSize);
            mv.addObject("complaintList", complaintList);
            mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
            mv.setViewName("propertyservice/complaints");
        } else {

            mv.setViewName("no-default-house");
        }
        return mv;
    }

    /**
     * 添加投诉页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/complaintview")
    public ModelAndView complaintView(
            @RequestParam String openid,
            @PathVariable String signinName) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);
        mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
        mv.setViewName("propertyservice/complaint_add");
        return mv;
    }

    //新增投诉
    @RequestMapping(value = "/do/addcomplaint", method = RequestMethod.POST)
    public ModelAndView addComplaintInfo(
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "imgs[]", required = false) String[] imgs,
            @ModelAttribute ComplaintInfo complaintInfo) throws TranscationalException {

        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Long houseInfoId = (Long) userWxMap.get("defaultHouseId");
        complaintInfo.setHouseInfoId(houseInfoId);
        complaintInfo.setHousingEstateId(estateId);
        ModelAndView mv = new ModelAndView();
        int flag = propertyServiceService.addComplaintInfo(complaintInfo,imgs);
//        if (flag > 0) {
            mv.setViewName("redirect:/" + signinName + "/service/complaintlist");
//        }
        mv.addObject("signinName", signinName);
        mv.addObject("openid", openid);
        mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
        return mv;
    }

    //环保积分
    @RequestMapping(value = "/integralslist")
    public ModelAndView getWasteIntegral(
            @RequestParam String openid,
            @PathVariable String signinName,
            @RequestParam(value = "flag", defaultValue = "iHistory") String flag,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Object houseIdObj = userWxMap.get("defaultHouseId");
        if(null != houseIdObj) {
            Long houseInfoId = (Long) userWxMap.get("defaultHouseId");

            //我的积分
            Map<String, Object> integralInfo = wasteIntegralService.queryWasteIntegralByHouseInfoId(estateId, houseInfoId);
            if (integralInfo != null) {
                mv.addObject("integralInfo", integralInfo);
            } else {
                integralInfo = new HashMap<>();
                integralInfo.put("housingEstateId", "0");
                integralInfo.put("houseOwnerId", "0");
                integralInfo.put("validIntegral", "0");
                integralInfo.put("usedIntegral", "0");
                integralInfo.put("totalIntegral", "0");
                integralInfo.put("lastTimeStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                integralInfo.put("houseInfoId", "0");
                mv.addObject("integralInfo", integralInfo);
            }
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("housingEstateId", estateId);
            paramMap.put("houseInfoId", houseInfoId);
            paramMap.put("pageIndex", pageIndex);
            paramMap.put("pageSize", pageSize);
            switch (flag) {
                case "iHistory"://积分查询
                    Page<Map<String, Object>> integralHistoryPage = wasteIntegralService.queryWasteIntegralLogList(paramMap);
                    mv.addObject("integralHistoryPage", integralHistoryPage);
                    break;
                case "iExchange"://积分兑换
                    Page<Map<String, Object>> integralExchangePage = wasteIntegralService.queryExchangeLogList(paramMap);
                    mv.addObject("integralExchangePage", integralExchangePage);
                    break;
                case "iAdjust"://积分调整
                    Page<Map<String, Object>> integralAdjustPage = wasteIntegralService.queryWasteIntegralChangeList(paramMap);
                    mv.addObject("integralAdjustPage", integralAdjustPage);
                    break;
                default:
                    integralHistoryPage = wasteIntegralService.queryWasteIntegralLogList(paramMap);
                    mv.addObject("integralHistoryPage", integralHistoryPage);
                    break;

            }

            mv.addObject("flag", flag);
            mv.addObject("houseInfo",houseService.queryHouseInfoById(houseInfoId));
            mv.addObject("openid",openid);
            mv.addObject("signinName", signinName);
            mv.setViewName("propertyservice/integral");
        } else {
            mv.setViewName("no-default-house");
        }
        return mv;
    }

    /**
     * 积分规格说明
     *
     * @return
     */
    @RequestMapping(value = "/integralintro")
    public ModelAndView integralIntroView(
            @RequestParam String openid,
            @PathVariable String signinName) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> userWxMap = getUserWx(signinName, openid);
        Long estateId = (Long) userWxMap.get("defaultEstateId");
        Object houseIdObj = userWxMap.get("defaultHouseId");
        List<Map<String, Object>> wasteInfoList = wasteIntegralService.queryWasteInfo(estateId);
        mv.addObject("wasteInfoList",wasteInfoList);
        mv.addObject("signinName",signinName);
        mv.addObject("openid",openid);
        mv.setViewName("propertyservice/integralintro");
        return mv;
    }

}
