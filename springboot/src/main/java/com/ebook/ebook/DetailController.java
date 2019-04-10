package com.ebook.ebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@RestController
//@RequestMapping("/ebooks")
public class DetailController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/detail")
    public String list(String name){
        /*从数据库获取数据*/
        String sql = "select * from books where book_name='"+name+"'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);

        Detail detail = new Detail();
        detail.setName(name);

        for(Map<String,Object> map :list){
            Set<Entry<String,Object>> entries = map.entrySet();
            if(entries!=null){
                Iterator<Entry<String,Object>> iterator = entries.iterator();
                while(iterator.hasNext()){
                    Entry<String,Object> entry = (Entry<String,Object>)iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();

                    /* set values to Detail object*/
                    setDetail(detail,key,value);
                }
            }
        }

        //findDetail(detail);

        String str="";
        str +=  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Detail</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/css2.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"header\">\n" +
                "        <a id=\"logo\">\n" +
                "            <img src=\"./img/logo.png\">\n" +
                "            <span>\n" +
                "                E-BOOK\n" +
                "            </span>\n" +
                "        </a>\n" +
                "    </div>\n" +
                " <div class=\"sidebar\">\n" +
                "            <div class=\"sidebar-inner-index\">\n" +
                "            <ul class=\"main-menu\">\n" +
                "                <li class=\"nav-dropdown-container\">\n" +
                "                    <a class=\"nav-link\">分类</a>\n" +
                "                    <span class=\"arrow\"></span>\n" +
                "                    <ul class=\"nav-dropdown\">\n" +
                "                        <li>计算机</li>\n" +
                "                        <li>小说</li>\n" +
                "                        <li>绘本</li>\n" +
                "                        <li>...</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "            </div>\n" +
                "</div>"+
                "    <div class=\"intro\">\n" +
                "        <a class=\"one-book-big\">\n" +
                "            <img src=\"./img/"+detail.img+"\">\n" +
                "\n" +
                "        </a>\n" +
                "        <div class=\"information\">\n" +
                "            <ul id=\"information\">\n" +
                "                <li>书名："+detail.cname+"</li>\n" +
                "                <li>作者："+detail.writer+" </li>\n" +
                "                <li>ISBN : "+detail.isbn+"</li>\n" +
                "                <li>库存 : "+detail.storage+"</li>\n" +
                "            </ul>\n" +
                "            <div>\n" +
                "            <div class=\"buy\">\n" +
                "                "+detail.price+"元\n" +
                "            </div>\n" +
                "            <div class=\"buy\">\n" +
                "                立即购买\n" +
                "            </div>\n" +
                "            <div class=\"buy\">\n" +
                "                加入购物车\n" +
                "            </div>\n" +
                "            </div>\n" +
                "              <p class='content'> 简介:" + detail.book_intro + "</p>\n" +
                "              <p class='content'> 作者:" + detail.writer_intro + "</p>\n" +
                "              <p class='content'> 评价:" + detail.book_comment + "</p>"+
                "        </div>";
        return str;
    }
    private void setDetail(Detail detail, Object key, Object value){
        if(key.toString().equals("cname")){
            detail.setCname(value.toString());
        }
        else if(key.toString().equals("writer")){
            detail.setWriter(value.toString());
        }
        else if(key.toString().equals("image")){
            detail.setImg(value.toString());
        }
        else if(key.toString().equals("isbn")){
            detail.setIsbn(value.toString());
        }
        else if(key.toString().equals("price")){
            detail.setPrice((float)value);
        }
        else if(key.toString().equals("storage")){
            detail.setStorage((int)value);
        }
        else if(key.toString().equals("sales")){
            detail.setSales((int)value);
        }
        else if(key.toString().equals("book_intro")){
            detail.setBook_intro(value.toString());
        }
        else if(key.toString().equals("writer_intro")){
            detail.setWriter_intro(value.toString());
        }
        else if(key.toString().equals("book_comment")){
            detail.setBook_omment(value.toString());
        }
    }

    /*
    public void findDetail(Detail detail){
        if(detail.name.equals("ics")){
            detail.setCname("深入理解计算机系统");
            detail.setWriter("（美）布赖恩特（Bryant,R.E.） 等");
            detail.setIsbn("978711154493");
            detail.setStorage(25);
            detail.setPrice(239);
            detail.setDetail("。。。");
            detail.setWriter_intro("，，，");
        }
        else{
            detail.setCname(detail.name);
            detail.setWriter("000");
            detail.setIsbn("000");
            detail.setStorage(0);
            detail.setPrice(100);
            detail.setDetail("introduction");
            detail.setWriter_intro("writer");
        }
    }
    */
}