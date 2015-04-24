package com.lezhai365.wap.controller.propertyservice;

import com.lezhai365.common.model.CacheUser;
import com.lezhai365.common.model.Page;
import com.lezhai365.pms.model.ComplaintInfo;
import com.lezhai365.pms.model.FaultInfo;
import com.lezhai365.pms.spi.system.IPmsParameterService;
import com.lezhai365.pms.spi.waste.IWasteIntegralService;
import com.lezhai365.pms.spi.wechat.IPropertyServiceService;
import com.lezhai365.wap.controller.BaseController;
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
@RequestMapping(value = "/{pmcId}/service")
public class PropertyServiceController extends BaseController {
    public static Long housingEstateId = 103558l;
    public static Long houseInfoId = 3464l;
    @Autowired
    IPropertyServiceService propertyServiceService;
    @Autowired
    IWasteIntegralService wasteIntegralService;
    @Autowired
    IPmsParameterService pmsParameterService;

    //账单查询
    @RequestMapping(value = "/billslist")
    public ModelAndView getBillsList(
            HttpServletResponse response,
            @PathVariable Long pmcId,
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();

        CacheUser user = getCacheUser(response);
        if(user != null ){
            Long estateId = 103558l;
            Long houseInfoId = 3464l;
            Page<Map<String,Object>> billsPage = propertyServiceService.queryPayBillsListByHouseInfoId(houseInfoId, estateId, pageIndex, pageSize);
            List<Map<String,Object>> billsList = billsPage.getContent();

            /**
             * {
             *   "2015-02":[{},{}]
             *   "2015-03":[{},{}]
             *   "2015-04":[{},{}]
             *   "2015-05":[{},{}]
             * }
             */
            Map<String,List<Object>> billsMap = new HashMap<>();
            for(Map<String,Object> map : billsList){
                String key = (String) map.get("month");
                if (billsMap.containsKey(key)){
                    List value= billsMap.get(key);
                    value.add(map);
                } else {
                    List value = new ArrayList<Object>();
                    value.add(map);
                    billsMap.put(key,value);
                }
            }

            Map<String,Object> sumNotPayMap = propertyServiceService.querySumNotPayBills(houseInfoId,estateId);

            mv.addObject("sumNotPayMap",sumNotPayMap);
            mv.addObject("billsMap",billsMap);
            mv.setViewName("propertyservice/paybills");
            mv.addObject("pmcId", pmcId);
        } else{
            mv.setViewName("redirect:/account/signin");
        }
        return mv;
    }



    //报修查询
    @RequestMapping(value="/faultlist")
    public ModelAndView getFaultInfoList(
            HttpServletResponse response,
            @PathVariable Long pmcId,
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();

        CacheUser user = getCacheUser(response);
        if(user != null ){
            Long estateId = 103558l;
            Long houseInfoId = 3464l;
            Page<Map<String,Object>> faultInfoList = propertyServiceService.queryFaultsByHouseInfoId(houseInfoId,estateId,pageIndex,pageSize);
            mv.addObject("faultInfoList",faultInfoList);
            mv.setViewName("propertyservice/faults");
            mv.addObject("pmcId", pmcId);

        } else{
            mv.setViewName("redirect:/account/signin");
        }
        return mv;
    }

    @RequestMapping(value="/faultview")
    public ModelAndView faultView(
            @PathVariable Long pmcId){
        ModelAndView mv = new ModelAndView();
        String repairFaultType="repair_fault_type";
        String repairEmergencyType="repair_emergency_type";

        //从session中获取物业公司的id
        Map<String,Object> repairFaultValue=null;
        Map<String,Object> repairEmergencyValue=null;
        //当前小区id
        repairFaultValue=pmsParameterService.queryInitParamsValue(repairFaultType,pmcId,housingEstateId);
        repairEmergencyValue=pmsParameterService.queryInitParamsValue(repairEmergencyType,pmcId,housingEstateId);
        mv.addObject("repairFaultValue",repairFaultValue);
        mv.addObject("repairEmergencyValue",repairEmergencyValue);
        mv.addObject("pmcId",pmcId);
        mv.setViewName("propertyservice/faults_add");
        return mv;
    }

    //新增报修
    @RequestMapping(value="/do/addfault",method = RequestMethod.POST)
    public ModelAndView addFaultInfo(
            @PathVariable Long pmcId,
            @ModelAttribute FaultInfo faultInfo){
        ModelAndView mv = new ModelAndView();
        faultInfo.setHousingEstateId(housingEstateId);
        faultInfo.setHouseInfoId(houseInfoId);

        int flag = propertyServiceService.addFaultInfo(faultInfo);
        if(flag>0){
            mv.setViewName("redirect:/service/faultlist");
        }
        mv.addObject("pmcId",pmcId);
        return mv;
    }

    //投诉查询
    @RequestMapping(value="/complaintlist")
    public ModelAndView getComplaintsList(
            @PathVariable Long pmcId,
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
        Long estateId = 103558l;
        Long houseInfoId = 3464l;
        Page<Map<String,Object>> complaintList = propertyServiceService.queryComplaintByHouseInfoId(houseInfoId,estateId,pageIndex,pageSize);
        mv.addObject("complaintList",complaintList);
        mv.addObject("pmcId",pmcId);
        mv.setViewName("propertyservice/complaints");
        return mv;
    }

    /**
     * 添加投诉页面跳转
     * @return
     */
    @RequestMapping(value="/complaintview")
    public ModelAndView complaintView(
            @PathVariable Long pmcId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("pmcId",pmcId);
        mv.setViewName("propertyservice/complaint_add");
        return mv;
    }

    //新增投诉
    @RequestMapping(value="/do/addcomplaint")
    public ModelAndView addComplaintInfo(
            @PathVariable Long pmcId,
            @ModelAttribute ComplaintInfo complaintInfo){
        complaintInfo.setHouseInfoId(houseInfoId);
        complaintInfo.setHousingEstateId(housingEstateId);
        ModelAndView mv = new ModelAndView();
        int flag = propertyServiceService.addComplaintInfo(complaintInfo);
        if(flag>0){
            mv.setViewName("redirect:/"+pmcId+"/service/complaintlist");
        }
        mv.addObject("pmcId",pmcId);
        return mv;
    }

    //环保积分
    @RequestMapping(value="/integralslist")
    public ModelAndView getWasteIntegral(
            @RequestParam String flag,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex){
        ModelAndView mv = new ModelAndView();
        //我的积分
        Map<String, Object> integralInfo = wasteIntegralService.queryWasteIntegralByHouseInfoId(housingEstateId, houseInfoId);
        if (integralInfo != null){
            mv.addObject("integralInfo",integralInfo);
        }else{
            integralInfo = new HashMap<>();
            integralInfo.put("housingEstateId", "0");
            integralInfo.put("houseOwnerId", "0");
            integralInfo.put("validIntegral", "0");
            integralInfo.put("usedIntegral", "0");
            integralInfo.put("totalIntegral", "0");
            integralInfo.put("lastTimeStr", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            integralInfo.put("houseInfoId", "0");
            mv.addObject("integralInfo",integralInfo);
        }
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("housingEstateId", housingEstateId);
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageIndex", pageIndex);
        paramMap.put("pageSize", pageSize);
        switch (flag){
                case "iHistory"://积分查询
                    Page<Map<String,Object>>  integralHistoryPage = wasteIntegralService.queryWasteIntegralLogList(paramMap);
                    mv.addObject("integralHistoryPage",integralHistoryPage);
                    break;
                case "iExchange"://积分兑换
                    Page<Map<String, Object>> integralExchangePage = wasteIntegralService.queryExchangeLogList(paramMap);
                    mv.addObject("integralExchangePage",integralExchangePage);
                    break;
                case "iAdjust"://积分调整
                    Page<Map<String, Object>> integralAdjustPage = wasteIntegralService.queryWasteIntegralChangeList(paramMap);
                    mv.addObject("integralAdjustPage",integralAdjustPage);
                    break;
                default:
                    integralHistoryPage = wasteIntegralService.queryWasteIntegralLogList(paramMap);
                    mv.addObject("integralHistoryPage",integralHistoryPage);
                    break;

        }
        mv.setViewName("propertyservice/integral");
        mv.addObject("flag",flag);
        return mv;
    }



}
