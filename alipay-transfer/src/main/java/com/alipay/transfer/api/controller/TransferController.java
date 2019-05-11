package com.alipay.transfer.api.controller;

import com.alipay.common.exception.TransferException;
import com.alipay.common.request.TransferRequest;
import com.alipay.common.response.ApiCode;
import com.alipay.common.response.ApiResponse;
import com.alipay.common.utils.access.AcessCheck;
import com.alipay.common.utils.log.LogOutput;
import com.alipay.common.utils.param.ParamCheckUtils;
import com.alipay.transfer.api.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author renzhiqiang
 * @Description 转账入口
 * @Date 2019-04-09
 **/
@Api(value = "转账入口", description = "实现用户之间转账逻辑")
@RequestMapping(value = "/account")
@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @ApiOperation(value = "转账", httpMethod = "POST")
    @AcessCheck(desc = "转账接入校验")
    @LogOutput(desc = "转账接口")
    @PostMapping(value = "/transfer")
    public ApiResponse transfer(@RequestBody TransferRequest request){
        //参数校验
        boolean checkResult = ParamCheckUtils.checkValid(request.getAmount(), request.getPayAccount(), request.getReciveAccount(), request.getTransferId());

        if (!checkResult){
            return ApiResponse.buildResponse(ApiCode.CODE_CONTENT_EMPTY, "参数有null值!");
        }

        try{
            //正常调用内部逻辑
            boolean result = transferService.transfer(request);
            if (result){
                return ApiResponse.buildSucessResponse("转账成功!");
            }else {
                return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "转账失败!");
            }
        }catch (TransferException e){
            //捕获到转账异常
            return ApiResponse.buildResponse(ApiCode.getApiCode(e.getCode()), e.getMsg());
        }catch (Exception e){
            //捕获到其他异常
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "转账失败!");
        }
    }
}
