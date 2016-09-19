package com.saltyfish.framework.controller;

import com.saltyfish.common.bean.Response;
import com.saltyfish.framework.service.AuthService;
import com.saltyfish.framework.service.FileService;
import com.saltyfish.framework.service.ProjectDetailService;
import com.saltyfish.framework.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weck on 16/9/19.
 * <p>
 * 工程明细请求
 */
@RequestMapping("/projectDetail")
@RestController
public class ProjectDetailController {

    @Autowired
    private ProjectDetailService projectDetailService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private AuthService authService;

    @Autowired
    private FileService fileService;

    /**
     * 添加工程明细的基本信息
     *
     * @param userId                用户id
     * @param token                 登录token
     * @param timeStamp             时间戳
     * @param projectName           工程名称
     * @param constructTime         修建时间
     * @param constructUnit         修建单位
     * @param sumFactInvestment     实际到位总投资
     * @param sumEstimateInvestment 概算总投资
     * @param others                其他
     * @return 保存的工程明细id
     */
    @RequestMapping("/addProjectDetail")
    public Response addProjectDetail(@RequestParam("userId") Integer userId,
                                     @RequestParam("token") String token,
                                     @RequestParam("timeStamp") Long timeStamp,
                                     @RequestParam("projectName") String projectName,
                                     @RequestParam(value = "constructTime", required = false, defaultValue = "") String constructTime,
                                     @RequestParam(value = "constructUnit", required = false, defaultValue = "") String constructUnit,
                                     @RequestParam(value = "sumFactInvestment", required = false, defaultValue = "") String sumFactInvestment,
                                     @RequestParam(value = "sumEstimateInvestment", required = false, defaultValue = "") String sumEstimateInvestment,
                                     @RequestParam(value = "others", required = false, defaultValue = "") String others) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                Integer projectDetailId = projectDetailService.saveCommonItems(projectName, constructTime, constructUnit, sumEstimateInvestment, sumFactInvestment, others, timeStamp);
                Map<String, Object> data = new HashMap<>();
                data.put("projectDetailId", projectDetailId);
                response.setCode(HttpStatus.OK.value());
                response.setData(data);
                return response;
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加工程的批复文件
     *
     * @param userId          用户id
     * @param token           登录token
     * @param timeStamp       时间戳
     * @param projectDetailId 工程明细id
     * @param file            文件打包
     * @param grade           行政单位等级
     * @param symbol          批复问号
     * @param approvalTime    批复时间
     * @return 操作结果
     */
    @RequestMapping("/addApproval")
    public Response addApproval(@RequestParam("userId") Integer userId,
                                @RequestParam("token") String token,
                                @RequestParam("timeStamp") Long timeStamp,
                                @RequestParam("projectDetailId") Integer projectDetailId,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "grade", required = false, defaultValue = "国家") String grade,
                                @RequestParam(value = "symbol", required = false, defaultValue = "") String symbol,
                                @RequestParam(value = "approvalTime", required = false, defaultValue = "") String approvalTime) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                String filePath = "";
                if (file != null) {
                    filePath = fileService.saveFile(file, timeStamp);
                }
                projectDetailService.saveApproval(projectDetailId, filePath, grade, symbol, approvalTime, timeStamp);
                return responseService.success(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.saveFileError(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加工程明细验收文件
     *
     * @param userId          用户id
     * @param token           登录token
     * @param timeStamp       实践戳
     * @param projectDetailId 工程明细id
     * @param file            验收文件
     * @param grade           等级
     * @param checkTime       验收时间
     * @return 操作结果
     */
    @RequestMapping("/addCheck")
    public Response addCheck(@RequestParam("userId") Integer userId,
                             @RequestParam("token") String token,
                             @RequestParam("timeStamp") Long timeStamp,
                             @RequestParam("projectDetailId") Integer projectDetailId,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam(value = "grade", required = false, defaultValue = "国家") String grade,
                             @RequestParam(value = "checkTime", required = false, defaultValue = "") String checkTime) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                String filePath = "";
                if (file != null) {
                    filePath = fileService.saveFile(file, timeStamp);
                }
                projectDetailService.saveCheck(projectDetailId, filePath, grade, checkTime, timeStamp);
                return responseService.success(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return responseService.saveFileError(response);
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }

    /**
     * 添加投资
     *
     * @param userId          用户id
     * @param token           登录token
     * @param timeStamp       时间戳
     * @param projectDetailId 工程明细id
     * @param invest          投资金额
     * @param investResource  投资来源
     * @param category        投资种类
     * @return 操作结果
     */
    @RequestMapping("/addInvestment")
    public Response addInvestment(@RequestParam("userId") Integer userId,
                                  @RequestParam("token") String token,
                                  @RequestParam("timeStamp") Long timeStamp,
                                  @RequestParam("projectDetailId") Integer projectDetailId,
                                  @RequestParam(value = "invest", required = false, defaultValue = "") String invest,
                                  @RequestParam(value = "investResource", required = false, defaultValue = "国家") String investResource,
                                  @RequestParam(value = "category", required = false, defaultValue = "概算投资") String category) {
        Response response = new Response();
        try {
            if (!authService.checkLogin(userId, token)) {
                return responseService.notLogin(response);
            } else if (!authService.checkAdmin(userId)) {
                return responseService.noAccess(response);
            } else {
                projectDetailService.saveInvestment(projectDetailId, invest, investResource, category, timeStamp);
                return responseService.success(response);
            }
        } catch (Exception e) {
            return responseService.serverError(response);
        }
    }
}