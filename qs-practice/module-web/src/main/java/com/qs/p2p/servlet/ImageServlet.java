package com.qs.p2p.servlet;

import com.qs.p2p.constant.Constants;
import com.qs.p2p.utils.BlowfishUtils;
import com.qs.p2p.web.CookieUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @apiNote : https://www.cnblogs.com/FlyHeLanMan/p/6293991.html
 * Created by scq on 2018-01-17 16:49:11
 */
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 生成验证码（字母数字混合）
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedImage img = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);

		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 68, 22);
		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		int index, len = ch.length;
		for (int i = 0; i < 4; i ++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22)); // 输出的字体和大小
			g.drawString("" + ch[index], (i * 15) + 3, 18); // 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, Constants.VALIDATE_CODE,
				BlowfishUtils.encrypt(sb.toString().toUpperCase(), Constants.COOKIE_NAME));
		ImageIO.write(img, "JPG", response.getOutputStream());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
