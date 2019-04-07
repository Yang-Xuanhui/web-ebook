package com.ebook.ebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailController {
    @ResponseBody
    @RequestMapping("/detail")
    public String list(String name){
        Detail detail = new Detail();
        detail.setName(name);
        findDetail(detail);

        String str="";
        str +=  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Detail</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/css2.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"header\">\n" +
                "        <a id=\"logo\">\n" +
                "            <img src=\"img/logo.png\">\n" +
                "            <span>\n" +
                "                E-BOOK\n" +
                "            </span>\n" +
                "        </a>\n" +
                "    </div>\n" +
                "    <div class=\"sidebar\">\n" +
                "        <div class=\"sidebar-inner-index\">\n" +
                "            <ul class=\"main-menu\">\n" +
                "                <li class=\"nav-dropdown-container\">\n" +
                "                    <a class=\"nav-link\">书单</a>\n" +
                "                    <span class=\"arrow\"></span>\n" +
                "                    <ul class=\"nav-dropdown\">\n" +
                "                        <li>深入理解计算机基础</li>\n" +
                "                        <li>数据库系统概念</li>\n" +
                "                        <li>向左走，向右走</li>\n" +
                "                        <li>...</li>\n" +
                "                    </ul>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"intro\">\n" +
                "        <a class=\"one-book-big\">\n" +
                "            <img src=\"img/cs.jpg\">\n" +
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
                "        </div><p class='content'> 简介: "+ detail.detail + "</p>" +
                "<p class='content'> 作者: " + detail.writer_intro + "</p>";
        return str;
    }
    public void findDetail(Detail detail){
        if(detail.name.equals("ics")){
            detail.setCname("深入理解计算机系统");
            detail.setWriter("（美）布赖恩特（Bryant,R.E.） 等");
            detail.setIsbn("978711154493");
            detail.setStorage(25);
            detail.setPrice(239);
            detail.setDetail("《深入理解计算机系统》 [4]  是理解计算机系统首选书目，是10余万程序员的共同选择。卡内基-梅隆、北京大学、清华大学、上海交通大学等国内外众多知名高校选用指定教材。从程序员视角全面剖析的实现细节，使读者深刻理解程序的行为，将所有计算机系统的相关知识融会贯通。\n" +
                    "                和第2版相比，本版 [4]  内容上最大的变化是，从以IA32和x86-64为基础转变为完全以x86-64为基础。主要更新如下：\n" +
                    "                基于x86-64，大量地重写代码，首次介绍对处理浮点数据的程序的机器级支持。\n" +
                    "                处理器体系结构修改为支持64位字和操作的设计。\n" +
                    "                引入更多的功能单元和更复杂的控制逻辑，使基于程序数据流表示的程序性能模型预测更加可靠。\n" +
                    "                扩充关于用GOT和PLT创建与位置无关代码的讨论，描述了更加强大的链接技术（比如库打桩）。\n" +
                    "                增加了对信号处理程序更细致的描述，包括异步信号安全的函数等。\n" +
                    "                采用新函数，更新了与协议无关和线程安全的网络编程。");
            detail.setWriter_intro("Randal E.Bryant：1981年在麻省理工学院获计算机科学博士学位，现任美国卡内基·梅隆大学计算机学院院长，是ACM和IEEE的双会士，多次获得这两个协会颁发的大奖。Bryant教授从事计算机系统方面的教学工作已超过20年，结合计算机体系结构课程多年的教学经验，他开始把关注点从如何设计计算机转移到如何使用程序员在更好地了解系统的情况下编写出更有效、更可靠的程序上来。");
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
}