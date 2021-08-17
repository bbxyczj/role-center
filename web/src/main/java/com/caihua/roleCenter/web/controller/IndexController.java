package com.caihua.roleCenter.web.controller;

import com.caihua.roleCenter.model.constants.Constants;
import com.caihua.roleCenter.web.annotation.NotLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * linggan on 2018/6/27
 * 首页模块
 */
@Controller
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 图片验证码
     * @param request
     * @param response
     */
    @GetMapping("/ImageMaskServlet")
    @NotLogin
    public void ImageMask(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        int width = 60, height = 20;
        //创建图象
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        //获取图形上下文
        Graphics graphics = image.getGraphics();
        //生成随机类
        Random random = new Random();
        //设定背景色
        graphics.setColor(getRandColor(200, 250));
        //验证码大小
        graphics.fillRect(0, 0, width, height);
        //设置字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        //graphics.setColor(getRandColor(160, 200));
        //随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        //取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            //0~9
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            //设置数字颜色
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //把随机产生的4位数画在图片上
            graphics.drawString(rand, 13 * i + 6, 16);
        }
        //放在session中
//        request.getSession().setAttribute("imageMask", sRand);
        redisTemplate.opsForValue().set(Constants.CODE_PREFIX+httpSession.getId(),sRand,60,TimeUnit.SECONDS);
        //图象生效
        graphics.dispose();
        //输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    //生成随机颜色
    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
