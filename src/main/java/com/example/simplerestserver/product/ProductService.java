package com.example.simplerestserver.product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();

    public List<Product> products() {
        String HTMLProducts = productRepository.HTMLSushiMasterCards();
        ArrayList<Product> products = new ArrayList<>(transformProductsFromHTMLSushiMasterCards(HTMLProducts));

        return products;
    }

    private List<Product> transformProductsFromHTMLSushiMasterCards(String htmlProducts) {
        Document docCustomConn = Jsoup.parse(htmlProducts);
        Elements productCards = docCustomConn.getElementsByClass("products-list-el");

        List products = productCards.stream()
                .map(this::transfrormProductFromHTMLSushiMasterCard)
                .collect(Collectors.toList());

        return products;
    }

    private Product transfrormProductFromHTMLSushiMasterCard(Element element) {
        // main info
        String title = element.getElementsByClass("title pointer").text();
        String href = element.getElementsByClass("title pointer").attr("href");
        String description = element.getElementsByClass("description").text();
        String image =  element.getElementsByClass("next-image-wrapper").first().child(0).attr("src");

        // weight
        String weightString =
                element.getElementsByClass("under-title flex align-center justify-between full-width").first().text().split(" ")[0];
        int weight = Integer.parseInt(weightString);

        // prices
        boolean hasSale = element.getElementsByClass("prices  ").first().childNodeSize() > 1;
        int price, salePrice = 0;
        String priceString = element.getElementsByClass("current-price").text().split(" ")[0];
        if (hasSale) {
            String oldPrice = element.getElementsByClass("old-price").text().split(" ")[0];
            String salePriceString = element.getElementsByClass("new-price").text().split(" ")[0];
            price = Integer.parseInt(oldPrice);
            salePrice = Integer.parseInt(salePriceString);
        } else {
            price = Integer.parseInt(priceString);
        }

        return new Product(title, description, weight, image, href, price, salePrice);
    }
}