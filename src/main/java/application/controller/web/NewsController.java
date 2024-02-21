package application.controller.web;

import application.model.viewmodel.common.ObjectProductVM;
import application.model.viewmodel.home.HomeLandingVM;
import application.model.viewmodel.news.NewsVM;
import application.untity.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tin-tuc")
public class NewsController extends BaseController {

    @GetMapping(value = "")
    public String news(Model model,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal,
                  @Valid @ModelAttribute("productname") ObjectProductVM productName){

        HomeLandingVM vm = new HomeLandingVM();

        List<NewsVM> listNews = new ArrayList<>();
        try
        {
            Document doc = Jsoup.connect("https://doanhnghiepvn.vn/991/the-gioi-di-dong").get();
            for (int i=0; i<=5; i++){
                NewsVM newsVM = new NewsVM();
                newsVM.setName("Doanh Nghiệp");
                Element post = doc.getElementsByClass("post-layout").get(i);
                if( post!= null) {
                    Element a = post.getElementsByTag("a").first();
                    String link = "https://doanhnghiepvn.vn" + a.attr("href");

                    newsVM.setLink(link);

                    Element b = post.getElementsByTag("img").first();
                    String image =  b.attr("src");

                    newsVM.setImage(image);
                    Element c = post.getElementsByTag("time").first();

                    Element d = post.getElementsByClass("blog-header").first();
                    String tittle = d.text();

                    newsVM.setTitle(tittle);

                    String s1 =  c.attr("datetime");
                    DateUtils dateUtils = new DateUtils();
                    String e = dateUtils.PlusMinus(s1);
                    newsVM.setDate(e);

                }
                listNews.add(newsVM);
            }



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        vm.setNewsVMList(listNews);
        model.addAttribute("vm",vm);

        totalCart(model,response,request,principal);

        return "news";
    }

}
