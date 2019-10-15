package cn.shinelon.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Date;

public class ImageCode extends ValidateCode{
	private BufferedImage bufferedImage;
	
	public ImageCode(BufferedImage bufferedImage,String code, int expireIn) {
		super(code,expireIn);
		this.bufferedImage = bufferedImage;
	}
	
	
	public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
		super(code,expireTime);
		this.bufferedImage = bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

}
