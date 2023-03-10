package com.stonezpl.hr.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stonezpl.hr.common.pojo.CommonResult;
import com.stonezpl.hr.controller.vo.RecordListReqVO;
import com.stonezpl.hr.entity.WxRecord;
import com.stonezpl.hr.service.IWxRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 记录表 前端控制器
 * </p>
 *
 * @author 张珮磊
 * @since 2023-03-08
 */
@Tag(name = "备忘录信息")
@RestController
@RequestMapping("/wxRecord")
public class WxRecordController {

    @Resource
    private IWxRecordService wxRecordService;

    @Operation(summary = "保存备忘录")
    @PostMapping("/saveRecord")
    public CommonResult<Boolean> saveRecord(@RequestBody WxRecord wxRecord) {
        return CommonResult.success(wxRecordService.save(wxRecord));
    }

    @Operation(summary = "获取备忘录列表")
    @PostMapping("/list")
    public CommonResult<List<WxRecord>> list(@RequestBody RecordListReqVO recordListReqVO) {
        List<WxRecord> wxRecords = wxRecordService.list(new LambdaQueryWrapper<WxRecord>()
                .eq(WxRecord::getOpenId, recordListReqVO.getOpenId()));
        return CommonResult.success(wxRecords);
    }

}
