package com.jiehfut.headline.controller;

/**
 * ClassName: PortalController
 * Package: com.jiehfut.headline.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:21
 * @Version 1.0
 */

import com.jiehfut.headline.common.Result;
import com.jiehfut.headline.pojo.HeadlineQueryVo;
import com.jiehfut.headline.pojo.NewsType;
import com.jiehfut.headline.service.NewsHeadlineService;
import com.jiehfut.headline.service.NewsTypeService;
import com.jiehfut.headline.service.NewsUserService;
import com.jiehfut.headline.service.impl.NewsHeadlineServiceImpl;
import com.jiehfut.headline.service.impl.NewsTypeServiceImpl;
import com.jiehfut.headline.service.impl.NewsUserServiceImpl;
import com.jiehfut.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户控制器
 * 那些不需要登录，不需要做增删改的门户页面的请求都放在这里
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController {

    private NewsTypeService newsTypeService = new NewsTypeServiceImpl();
    private NewsUserService newsUserService = new NewsUserServiceImpl();
    private NewsHeadlineService newsHeadlineService = new NewsHeadlineServiceImpl();

    /**
     * 查询所有的新闻类型的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的新闻类型，装入 Result 对象响应给前端
        List<NewsType> newsTypeList = newsTypeService.findAll();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }


    /**
     * 用户在成功登陆后，跳转到首页面，分页带条件查询所有头条
     * @param req
     * {
     *     "keyWords":"马斯克", // 搜索标题关键字
     *     "type":0,           // 新闻类型
     *     "pageNum":1,        // 页码数
     *     "pageSize":"10"     // 页大小
     * }
     * @param resp
     * {
     *     "code":"200",
     *  	"message":"success"
     *  	"data":{
     *     	"pageInfo":{
     *     		"pageData":[                           // 本页的数据
     *     			                {
     *     				"hid":"1",                     // 新闻id
     *     				"title":"宣布 ... ...",   // 新闻标题
     *     				"type":"1",                    // 新闻所属类别编号
     *     				"pageViews":"40",              // 新闻浏览量
     *     				"pastHours":"3" ,              // 发布时间已过小时数
     *     				"publisher":"1"                // 发布用户ID
     * 				},
     * 				{
     *     				"hid":"1",                     // 新闻id
     *     				"title":"宣布 ... ...",   // 新闻标题
     *     				"type":"1",                    // 新闻所属类别编号
     *     				"pageViews":"40",              // 新闻浏览量
     *     				"pastHours":"3",              // 发布时间已过小时数
     *     				"publisher":"1"                // 发布用户ID
     * 				},
     * 				{
     *     				"hid":"1",                     // 新闻id
     *     				"title":"宣布 ... ...",   // 新闻标题
     *     				"type":"1",                    // 新闻所属类别编号
     *     				"pageViews":"40",              // 新闻浏览量
     *     				"pastHours":"3",               // 发布时间已过小时数
     *     				"publisher":"1"                // 发布用户ID
     * 				}
     *     		],
     * 			"pageNum":1,    //页码数
     * 			"pageSize":10,  // 页大小
     * 			"totalPage":20, // 总页数
     * 			"totalSize":200 // 总记录数
     * 		}
     * 	}
     * }
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        
        // 将参数传递给服务层进行分页查询
        /**
         * 返回的是所有的头条信息 map
         * pageInfo 种包含以下信息
         * "pageData":所有的头条信息数组
         * "pageNum":1,    //页码数
         * "pageSize":10,  // 页大小
         * "totalPage":20, // 总页数
         * "totalSize":200 // 总记录数
         */
        Map pageInfo = newsHeadlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo", pageInfo);

        // 分页查询的结果转换为 JSON 相应给客户端
        WebUtil.writeJson(resp, Result.ok(data));
    }
}
