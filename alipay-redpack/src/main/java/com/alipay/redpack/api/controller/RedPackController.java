package com.alipay.redpack.api.controller;

import com.alipay.common.exception.RedPackException;
import com.alipay.common.request.RedPackRequest;
import com.alipay.common.response.ApiCode;
import com.alipay.common.response.ApiResponse;
import com.alipay.common.utils.access.AcessCheck;
import com.alipay.common.utils.log.LogOutput;
import com.alipay.common.utils.param.ParamCheckUtils;
import com.alipay.redpack.api.entity.RedPacket;
import com.alipay.redpack.api.service.RedPackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author renzhiqiang
 * @Description 红包入口
 * @Date 2019-04-10
 **/
@Api(value = "红包入口", description = "提供创建红包和抢红包接口")
@RestController
@RequestMapping(value = "/redpack")
public class RedPackController {
    @Autowired
    private RedPackService redPackService;

    @ApiOperation(value = "创建红包", httpMethod = "POST")
    @AcessCheck(desc = "创建红包接入校验")
    @LogOutput(desc = "创建红包接口")
    @PostMapping(value = "/create")
    public ApiResponse createRedPack(@RequestBody RedPackRequest request){
        //参数校验
        boolean checkResult = ParamCheckUtils.checkValid(request.getAmount(), request.getRemark(), request.getAccountId(), request.getRedPackCount());

        if (!checkResult){
            return ApiResponse.buildResponse(ApiCode.CODE_CONTENT_EMPTY, "参数有null值!");
        }

        try{
            RedPacket redPacket = redPackService.createRedPack(request);
            if (redPacket != null){
                //创建红包成功，返回红包信息
                return ApiResponse.buildSucessResponse(redPacket);
            }else {
                //创建红包失败，返回失败信息
                return ApiResponse.buildAccessError("创建红包失败!");
            }
        }catch (RedPackException e){
            return ApiResponse.buildResponse(ApiCode.getApiCode(e.getCode()), e.getMsg());
        }catch (Exception e){
            //捕获到其他异常
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "创建红包失败!");
        }
    }

    @ApiOperation(value = "创建红包", httpMethod = "POST")
    @AcessCheck(desc = "创建红包接入校验")
    @LogOutput(desc = "创建红包接口")
    @PostMapping(value = "/create")
    public ApiResponse grabRedPack(@RequestParam("accountId") int accountId, @RequestParam("redPackId")String redPackId){
        try{
            return ApiResponse.buildSucessResponse(redPackService.grabRedPack(accountId, redPackId));
        }catch (RedPackException e){
            return ApiResponse.buildResponse(ApiCode.getApiCode(e.getCode()), e.getMsg());
        }catch (Exception e){
            //捕获到其他异常
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "创建红包失败!");
        }
    }

}
