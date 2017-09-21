/*
 * 说明： 验证码生成工具类
 * 
 * 作者: 束炳林
 * 编写日期: 2014-11-20
 * 更新日期:
 * 
 */
package com.xiaocui.platform.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * 
 * @description 功能描述：验证码生成类
 * @author 作者: 束炳林
 * @createdate 创建日期 2014年11月28日 下午8:10:14
 */
public class ValidateCode {
	
	public final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
	private Random random = new Random();
	private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int width = 80;//图片宽
	private int height = 25;//图片高
	private int stringNum = 4;//随机产生字符数量
	
	private int rndSpacingX = 4; //随机字间距X
	private int rndSpacingY = 4; //随机字间距Y
	private int offsetX = 0; //偏移X
	private int offsetY = 16; //偏移Y
	private int fontSize = 18; //字号
	private int wordSpacing = 13;//字间距
	
	private int lineNum = 8; //干扰线数量
	private int pixeNosieNum = 50; //干扰点数量
	
	public int getStringNum() {
		return stringNum;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}
	
	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public int getPixeNosieNum() {
		return pixeNosieNum;
	}

	public void setPixeNosieNum(int pixeNosieNum) {
		this.pixeNosieNum = pixeNosieNum;
	}

	public int getRndSpacingX() {
		return rndSpacingX;
	}

	public void setRndSpacingX(int rndSpacingX) {
		this.rndSpacingX = rndSpacingX;
	}

	public int getRndSpacingY() {
		return rndSpacingY;
	}

	public void setRndSpacingY(int rndSpacingY) {
		this.rndSpacingY = rndSpacingY;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getWordSpacing() {
		return wordSpacing;
	}

	public void setWordSpacing(int wordSpacing) {
		this.wordSpacing = wordSpacing;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	private Color getRandColor(int fc,int bc){
	    if(fc > 255)
	        fc = 255;
	    if(bc > 255)
	        bc = 255;
	    int r = fc + random.nextInt(bc-fc-16);
	    int g = fc + random.nextInt(bc-fc-14);
	    int b = fc + random.nextInt(bc-fc-18);
	    return new Color(r,g,b);
	}
	
	public void CreateGifVCode(OutputStream os,String vcode)
	{
		AnimatedGifEncoder agf = new AnimatedGifEncoder();
		agf.start(os);
		agf.setQuality(10);

		agf.setDelay(1200);
		agf.setRepeat(0);
		
		for(int i=0;i<=stringNum;i++)
		{
			BufferedImage frame = CreateImageFrame(vcode);
			agf.addFrame(frame);
			frame.flush();
		}

		agf.finish();
	}
	
	public void CreateJPEGVCode(OutputStream os,String vcode)
	{
		try {
			BufferedImage frame = CreateImageFrame(vcode);
            ImageIO.write(frame, "JPEG", os);//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private BufferedImage CreateImageFrame(String vcode) {
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
	    Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
	    g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,fontSize));
        g.setColor(getRandColor(0, 255));

        //绘制干扰线
        drawLine(g,lineNum);
        
        //绘制噪点
        addPixeNosie(g,pixeNosieNum);
        
        //绘制验证码
        drawVCode(g,vcode);
        
        g.dispose();
                
        return image;
	}
	
	/*
     * 获得字体
     */
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,fontSize);
    }
	
    /*
     * 生成验证码
     */
    public String getRandVCode()
    {
    	//绘制随机字符
        String vcode = "";
        for(int i=1;i<=stringNum;i++){
        	vcode+=String.valueOf(randString.charAt(random.nextInt(randString.length())));
        }
        return vcode;
    }
    
    public static int getRandNumber(int n)
    {
    	int ans = 0;
        while(Math.log10(ans)+1<n)
            ans = (int)(Math.random()*Math.pow(10, n));
        return ans;
    }
    
	/*
     * 绘制字符串
     */
    private void drawVCode(Graphics g,String vcode){
    	for(int i=0;i<vcode.length();i++)
    	{
            Graphics2D g2d = (Graphics2D)g;
            g2d.setFont(getFont());
            g2d.setColor(getRandColor(0,255));
            
            String code = String.valueOf(vcode.charAt(i));
            
            // 换算弧度
            //double radian = 角度 * Math.PI / 180;
            //文字角度
            //g2d.rotate(radian);
            
            g2d.translate(random.nextInt(rndSpacingX), random.nextInt(rndSpacingY));
            g2d.drawString(code, offsetX+wordSpacing*i, offsetY);
    	}
    }
    /*
     * 绘制干扰线
     */
    private void drawLine(Graphics g, int count){
    	
    	for(int i=0;i<=count;i++)
    	{
    		int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(width);
            int yl = random.nextInt(height);
            g.drawLine(x, y, x+xl, y+yl);
    	}
    }
    /*
     * 绘制干扰点
     */
    private void addPixeNosie(Graphics g, int count)
    {
        //画图片的前景干扰点
        for (int i = 0; i < count; i++)
        {
        	int x = random.nextInt(width);
            int y = random.nextInt(height);
            
            g.drawOval(x, y, 1, 1);
        }
    }
}
