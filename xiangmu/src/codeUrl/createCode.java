package codeUrl;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 支付二维码生成类
 * 
 * @author 杨名
 * 
 */
public class createCode {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createCodeByUrl(String urlContent) throws Exception {
		// 生成图片的宽度
		int width = 200;
		// 二维码图片的高度
		int height = 300;
		// 生成二维码图片格式
		String format = "png";
		// 生成二维码参数
		HashMap msg = new HashMap();
		// 字符格式设置
		msg.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// 等级设置
		msg.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		// 图片边距
		msg.put(EncodeHintType.MARGIN, 6);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(urlContent,
				BarcodeFormat.QR_CODE, width, height, msg);
		// 保存图片地址
		Random random = new Random();
		int i = Math.abs(random.nextInt());
		String url= "/images/" + i + ".png";
		String urlName = "images/" + i + ".png";
		Path file = new File("C:/Tomcat 5.5/webapps/xiangmu" + url)
				.toPath();
		MatrixToImageWriter.writeToPath(bitMatrix, format, file);
		return urlName;
	}
}
