/**
 * 
 */
package org.xdemo.example.springrestful.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xdemo.example.springrestful.entity.User;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @作者 贾姗姗
 * @邮件 jiashanshan.uh@haier.com
 * @日期 201904181121untack
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	public List<User> list=null;

	/**
	 * user路径下默认显示用户列表
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index(){
		if(list==null){
			list=getUserList();
		}
		ModelMap model=new ModelMap();
		model.addAttribute("list",list);
		
		return new ModelAndView("user/index",model);
	}
	
	/**
	 * 跳转到添加用户页面，约定优于配置，默认匹配文件/WEB-INF/views/user/add.jsp
	 */
	@RequestMapping("add")
	public void add(){}
	
	/**
	 * 新增保存用户
	 * @param user
	 * @return ModelAndView
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView addUser(User user){
		if(list==null){
			list=getUserList();
		}
		list.add(user);
		
		ModelMap model=new ModelMap();
		model.addAttribute("list",list);
		
		return new ModelAndView("user/index",model);
	}
	private BufferedImage image;
    private int imageWidth = 300;  //图片的宽度
    private int imageHeight = 500; //图片的高度
    public String graphicsGeneration(String name, String id, String classname, String imgurl) {
       int H_title = 30;     //头部高度
       int H_mainPic = 200;  //轮播广告高度
       int H_tip = 60;  //上网提示框高度
       int H_btn = 25;  //按钮栏的高度
       int tip_2_top = (H_title+H_mainPic);
       int btns_2_top = tip_2_top+H_tip+20;
       int btn1_2_top = btns_2_top+10;
       int btn2_2_top = btn1_2_top+H_btn;
       int shops_2_top = btn2_2_top+H_btn+20;
       int W_btn = 280;  //按钮栏的宽度
        
       image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
       //设置图片的背景色
       Graphics2D main = image.createGraphics();
       main.setColor(Color.white);
       main.fillRect(0, 0, imageWidth, imageHeight);
        
       //***********************页面头部
       Graphics title = image.createGraphics();
       //设置区域颜色
       title.setColor(new Color(143, 0, 0));
       //填充区域并确定区域大小位置
       title.fillRect(0, 0, imageWidth, H_title);
       //设置字体颜色，先设置颜色，再填充内容
       title.setColor(Color.white);
       //设置字体
       Font titleFont = new Font("宋体", Font.BOLD, 14);
       title.setFont(titleFont);
       title.drawString("my head", 100, (H_title)/2+5);

       //***********************插入中间广告图
       Graphics mainPic = image.getGraphics();
       BufferedImage bimg = null;
       try {
          bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
       } catch (Exception e) {}
    
       if(bimg!=null){
           mainPic.drawImage(bimg, 0, H_title, imageWidth, H_mainPic, null);
           mainPic.dispose();
       }
       //***********************设置下面的提示框
        
       Graphics2D tip = image.createGraphics();
       //设置区域颜色
       tip.setColor(new Color(255, 120, 89));
       //填充区域并确定区域大小位置
       tip.fillRect(0, tip_2_top, imageWidth, H_tip);
       //设置字体颜色，先设置颜色，再填充内容
       tip.setColor(Color.white);
       //设置字体
       Font tipFont = new Font("宋体", Font.PLAIN, 14);
       tip.setFont(tipFont);
       tip.drawString("登录成功，本次认证时间1小时", 60, tip_2_top+(H_tip)/2-10);
       tip.drawString("正在返回商家主页", 100, tip_2_top+(H_tip)/2+10);
        
        
        
       //***********************设置下面的按钮块
       //设置字体颜色，先设置颜色，再填充内容
       tip.setColor(Color.black);
       tip.drawString("您可以选择的操作：", 20, btns_2_top);
       tip.drawString("下面的小图标：", 20, shops_2_top);
       //***********************按钮
       Font btnFont = new Font("宋体", Font.BOLD, 14);
       Graphics2D btn1 = image.createGraphics();
       btn1.setColor(new Color(41,192 , 50));//#29C65A
       btn1.fillRect(10, btn1_2_top, W_btn, H_btn);
       btn1.setColor(Color.BLACK);
       btn1.drawRect(10, btn1_2_top, W_btn, H_btn);
       //btn1 文本
       btn1.setColor(Color.white);
       btn1.setFont(btnFont);
       btn1.drawString("单击我啊", 120, btn1_2_top+(H_btn/2)+5);
        
       Graphics2D btn2 = image.createGraphics();
       btn2.setColor(new Color(141,120 , 22));//#29C65A
       btn2.fillRect(10, btn2_2_top, W_btn, H_btn);
       btn2.setColor(Color.BLACK);
       btn2.drawRect(10, btn2_2_top, W_btn, H_btn);
       //btn2文本
       btn2.setColor(Color.white);
       btn2.setFont(btnFont);
       btn2.drawString("单击我啊", 120, btn2_2_top+(H_btn/2)+5);
    
       createImage("c:\\hehe.jpg");
       return "c:\\hehe.jpg";
        
   }
  //生成图片文件
    @SuppressWarnings("restriction")
    public void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
                FileOutputStream fos = new FileOutputStream(fileLocation);
                bos = new BufferedOutputStream(fos);
                 
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
                encoder.encode(image);
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(bos!=null){//关闭输出流
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
	@RequestMapping(method=RequestMethod.GET,value="{createPhoto}")
    public void createPhoto(HttpServletResponse response) throws FileNotFoundException {
	  String fileName=graphicsGeneration("ewew", "1", "12", "E:\\work_folder\\picture\\big_pic\\1.jpg");
//        return new ModelAndView("user/index",model);
	// 读到流中
      InputStream inStream = new FileInputStream(fileName);// 文件的存放路径
      // 设置输出的格式
      response.reset();
      response.setContentType("bin");
      response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
      // 循环取出流中的数据
      byte[] b = new byte[100];
      int len;
      try {
          while ((len = inStream.read(b)) > 0)
              response.getOutputStream().write(b, 0, len);
          inStream.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
	
	/**
	 * 查看用户详细信息
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping(method=RequestMethod.GET,value={"{id}/queryInfo","{id}/getInfo"})
	public ModelAndView viewUser(@PathVariable("id")String id){
		User user=findUserById(id);
		ModelMap model=new ModelMap();
		model.addAttribute("user",user);
		
		return new ModelAndView("user/view",model);
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	public String deleteUser(@PathVariable("id")String id){
		if(list==null){
			list=getUserList();
		}
		removeUserByUserId(id);
		return "suc";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("{id}/edit")
	public ModelAndView toEdit(@PathVariable("id")String id){
		
		User user=findUserById(id);
		ModelMap model=new ModelMap();
		model.addAttribute("user",user);
		
		return new ModelAndView("user/edit",model);
	}
	
	/**
	 * 更新用户并跳转到用户列表页面
	 * @param user
	 * @return ModelAndView
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public ModelAndView edit(User user){
		updateUser(user);
		return new ModelAndView("redirect:/user/");
	}
	
	
	/**
	 * 造10个用户
	 * @return List<User>
	 */
	private List<User> getUserList(){
		List<User> list=new ArrayList<User>();
		for(int i=0; i<100;i++){
			list.add(new User((i+1)+"","李四"+(i+1)));
		}
		return list;
	}
	/**
	 * 删除用户
	 * @param id
	 * @return List<User>
	 */
	private List<User> removeUserByUserId(String id){
		if(list==null)return null;
		for(User user:list){
			if(user.getUserId().equals(id)){
				list.remove(user);
				break;
			}
		}
		return list;
	}
	/**
	 * 查找用户
	 * @param id
	 * @return User
	 */
	private User findUserById(String id){
		User user=null;
		if(list==null)return null;
		for(User _user:list){
			if(_user.getUserId().equals(id)){
				user=_user;
				break;
			}
		}
		return user;
	}
	/**
	 * 更新用户
	 * @param user
	 */
	private void updateUser(User user){
		for(int i=0;i<list.size();i++){
		    User _user=list.get(i);
			if(_user.getUserId().equals(user.getUserId())){
				_user.setUserName(user.getUserName());
				list.set(i, _user);
				break;
			}
		}
	}
	
	
}
