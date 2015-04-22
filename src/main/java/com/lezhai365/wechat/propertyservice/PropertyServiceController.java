package com.lezhai365.wechat.propertyservice;

import com.lezhai365.common.model.Page;
import com.lezhai365.common.model.ResultObject;
import com.lezhai365.pms.model.ComplaintInfo;
import com.lezhai365.pms.model.FaultInfo;
import com.lezhai365.pms.spi.waste.IWasteIntegralService;
import com.lezhai365.pms.spi.wechat.IPropertyServiceService;
import com.lezhai365.wechat.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.controller
 * @link :  http://lezhai365.com
 * @created on   :  15-4-20
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value = "/service")
public class PropertyServiceController extends BaseController {

    @Autowired
    IPropertyServiceService propertyServiceService;
    @Autowired
    IWasteIntegralService wasteIntegralService;

    //账单查询
    @RequestMapping(value = "/billslist")
    public ModelAndView getBillsList(
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
        Long estateId = 110l;
        Long houseInfoId = 110l;
        Page<Map<String,Object>> billsList = propertyServiceService.queryPayBillsListByHouseInfoId(houseInfoId,estateId,pageIndex,pageSize);
        mv.addObject("billsList",billsList);
        mv.setViewName("");
        return mv;
    }



    //报修查询
    public ModelAndView getFaultInfoList(
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
        Long estateId = 110l;
        Long houseInfoId = 110l;
        Page<Map<String,Object>> faultInfoList = propertyServiceService.queryFaultsByHouseInfoId(houseInfoId,estateId,pageIndex,pageSize);
        mv.addObject("faultInfoList",faultInfoList);
        mv.setViewName("");

        return mv;
    }


    //新增报修
    public ModelAndView addFaultInfo(
            @ModelAttribute FaultInfo faultInfo){
        ModelAndView mv = new ModelAndView();
        int flag = propertyServiceService.addFaultInfo(faultInfo);
        if(flag>0){
            mv.setViewName("");
        }
        return mv;
    }

    //投诉查询
    public ModelAndView getComplaintsList(
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
        Long estateId = 110l;
        Long houseInfoId = 110l;
        Page<Map<String,Object>> complaintList = propertyServiceService.queryComplaintByHouseInfoId(houseInfoId,estateId,pageIndex,pageSize);
        mv.addObject("complaintList",complaintList);
        return mv;
    }
    //新增投诉
    public ModelAndView addComplaintInfo(
            @ModelAttribute ComplaintInfo complaintInfo){
        ModelAndView mv = new ModelAndView();
        int flag = propertyServiceService.addComplaintInfo(complaintInfo);
        if(flag>0){
            mv.setViewName("");
        }
        return mv;
    }

    //环保积分
    /**
     * 查询我的积分和累计消费积分
     * <pre>
     *     url:http://localhost/mobile/{version}/waste/wasteIntegral/houseInfo/list
     * </pre>
     * @return ResultObject 返回的值
     * <pre>
     *  *     ResultObject中数据的格式
     *       {
     *        success:[true|false]  //请求是否成功    true:请求成功 false:请求失败
     *        msg：'',              //服务端返回的提示信息
     *        code:'',              //请求状态码 200成功
     *        data:
    {
    "success": true,
    "msg": null,
    "code": "200",
    "data": {
    "id": 1,
    "housingEstateId": 101560, //小区ID
    "houseOwnerId": 2710,//业主ID
    "num": 2710,//房间编号
    "validIntegral": 3500,//有效积分
    "usedIntegral": 0,//使用积分
    "totalIntegral": 0,//总积分
    "qrCode": "6231251",//二维码编号
    "qrCodePic": null,//二维码图片
    "createdTime": 1425704928716,//添加时间
    "lastTime": 1425704928716,//最后积分时间
    "name": "王冬强",//业主姓名
    "mobilePhone": "13474294206"//业主电话
    },
    "login": true
    }
     * </pre>
     * @throws Exception
     */
    @RequestMapping(value = {"/wasteIntegral/houseInfo/list"},method = RequestMethod.GET)
    public ModelAndView queryWasteIntegralById(@RequestParam Long housingEstateId,
                                               @RequestParam Long houseInfoId) throws Exception {
        ResultObject resultObject = new ResultObject();
        int result = 0;
        Map<String, Object> integralInfo = wasteIntegralService.queryWasteIntegralByHouseInfoId(housingEstateId, houseInfoId);
        resultObject.setSuccess(true);
        resultObject.setCode("200");
        if (integralInfo != null){
            resultObject.setData(integralInfo);
        }else{
            integralInfo = new HashMap<>();
            integralInfo.put("housingEstateId", "0");
            integralInfo.put("houseOwnerId", "0");
            integralInfo.put("validIntegral", "0");
            integralInfo.put("usedIntegral", "0");
            integralInfo.put("totalIntegral", "0");
            integralInfo.put("lastTime", System.currentTimeMillis());
            integralInfo.put("houseInfoId", "0");
            resultObject.setData(integralInfo);
        }
        return null;
    }
    /**
     * 查询积分历史列表
     * <pre>
     *     url:http://localhost/mobile/{version}/waste/integralLog/list
     * </pre>
     * @return ResultObject 返回的值
     * <pre>
     *  *     ResultObject中数据的格式
     *       {
     *        success:[true|false]  //请求是否成功    true:请求成功 false:请求失败
     *        msg：'',              //服务端返回的提示信息
     *        code:'',              //请求状态码 200成功
     *        data:
     *        {
    "success": true,
    "msg": null,
    "code": "200",
    "data": {
    "pageIndex": 1,
    "next": 1,
    "previous": 1,
    "totalPage": 1,
    "totalElements": 7,
    "pageSize": 10,
    "pageList": [
    "1"
    ],
    "pageListSize": 10,
    "content": [
    {
    "id": 9,
    "housingEstateId": 101560, 小区ID
    "houseOwnerId": 2710, 业主ID
    "num": 2710, 房间ID
    "wasteIntegralId": null,  积分ID
    "wasteInfoId": null, 分类ID
    "integralGrade": 600, 本次积分
    "weight": 300, 本次垃圾重量
    "unitPrice": 200, 本次积分单价
    "isValided": 0, 是否有效
    "remarks": null,
    "createdTime": 1426219242652, 创建时间
    "name": "王冬强", 业主名称
    "mobilePhone": "13474294206", 业主电话
    "wasteName": null 垃圾名称
    }
    ],
    "offset": 10,
    "first": false,
    "last": true
    },
    "login": true
    }
     * </pre>
     * @throws Exception
     */
    @RequestMapping(value = "/integralLog/list",method = RequestMethod.GET)
    public ModelAndView queryWasteIntegraLogList(@RequestParam Long housingEstateId,
                                                 @RequestParam Long houseInfoId,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex){
        ResultObject resultObject=new ResultObject();
        Map<String,Object> paramMap = new HashMap();

        paramMap.put("housingEstateId", housingEstateId);
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageIndex", pageIndex);
        paramMap.put("pageSize", pageSize);

        Page<Map<String, Object>> integraLogPage = wasteIntegralService.queryWasteIntegralLogList(paramMap);
        resultObject.setData(integraLogPage);
        return null;
    }

    /**
     * 查询兑换商品列表
     * <pre>
     *     url:http://localhost/mobile/{version}/waste/exchangeLog/list
     * </pre>
     * @return ResultObject 返回的值
     * <pre>
     *  *     ResultObject中数据的格式
     *       {
     *        success:[true|false]  //请求是否成功    true:请求成功 false:请求失败
     *        msg：'',              //服务端返回的提示信息
     *        code:'',              //请求状态码 200成功
     *        data:
     *        {
    "success": true,
    "msg": null,
    "code": "200",
    "data": {
    "pageIndex": 1,
    "next": 1,
    "previous": 1,
    "totalPage": 1,
    "totalElements": 3,
    "pageSize": 10,
    "pageList": [
    "1"
    ],
    "pageListSize": 10,
    "content": [
    {
    "id": 6,
    "housingEstateId": 101560, 小区ID
    "houseOwnerId": 2710, 业主ID
    "num": 2710, 房间编号
    "wasteIntegralId": null, 积分编号
    "goodsName": "洗手液", 商品名称
    "goodsPrice": 200, 商品单价
    "goodsNumber": 1, 商品数量
    "needIntegral": 200,需要积分
    "usedIntegral": 200,使用积分
    "createdTime": 1426046424272,创建时间
    "name": "王冬强",业主名称
    "mobilePhone": "13474294206" 业主电话
    }]}
    }

     * </pre>
     * @throws Exception
     */
    @RequestMapping(value = "/exchangeLog/list",method = RequestMethod.GET)
    public ModelAndView queryExchangeLogList(@RequestParam Long housingEstateId,
                                             @RequestParam Long houseInfoId,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex){
        ResultObject resultObject=new ResultObject();
        Map<String,Object> paramMap = new HashMap();

        paramMap.put("housingEstateId", housingEstateId);
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageIndex", pageIndex);
        paramMap.put("pageSize", pageSize);

        Page<Map<String, Object>> exchangeLogPage = wasteIntegralService.queryExchangeLogList(paramMap);
        resultObject.setData(exchangeLogPage);
        return null;
    }

    /**
     * 查询积分调整历史
     * <pre>
     *     url:http://localhost/mobile/{version}/waste/integralChange/list
     * </pre>
     * @return ResultObject 返回的值
     * <pre>
     *  *     ResultObject中数据的格式
     *       {
     *        success:[true|false]  //请求是否成功    true:请求成功 false:请求失败
     *        msg：'',              //服务端返回的提示信息
     *        code:'',              //请求状态码 200成功
     *        data:
     *        {
    "success": true,
    "msg": null,
    "code": "200",
    "data": {
    "pageIndex": 1,
    "next": 1,
    "previous": 1,
    "totalPage": 1,
    "totalElements": 7,
    "pageSize": 10,
    "pageList": [
    "1"
    ],
    "pageListSize": 10,
    "content": [
    {
    "id": 11,
    "housingEstateId": 101560, //小区ID
    "houseOwnerId": 2710, //业主ID
    "wasteIntegralLogId": 3, //积分历史ID
    "oldIntegral": 800, //原始积分
    "newIntegral": 1000, //修改后积分
    "remarks": null, //备注
    "userId": null, //用户ID
    "updateTime": 1426044026758, //修改时间
    "name": "王冬强", //业主姓名
    "mobilePhone": "13474294206" //业主电话
    }
    ]
    }
     * </pre>
     * @throws Exception
     */
    @RequestMapping(value = {"/integralChange/list"},method = RequestMethod.GET)
    public ModelAndView queryIntegralLogList(@RequestParam Long housingEstateId,
                                             @RequestParam Long houseInfoId,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) throws Exception {
        ResultObject resultObject=new ResultObject();
        Map<String,Object> paramMap = new HashMap();

        paramMap.put("housingEstateId", housingEstateId);
        paramMap.put("houseInfoId", houseInfoId);
        paramMap.put("pageIndex", pageIndex);
        paramMap.put("pageSize", pageSize);

        Page<Map<String, Object>> exchangeLogPage = wasteIntegralService.queryWasteIntegralChangeList(paramMap);
        resultObject.setData(exchangeLogPage);
        return null;
    }





}
