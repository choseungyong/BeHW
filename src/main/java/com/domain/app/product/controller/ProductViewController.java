/*
package com.domain.app.product.controller;

import com.domain.app.product.domain.Product;
import com.domain.app.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productservice;

    @GetMapping
    public String list(Model model) {
        List<Product> products = productservice.getAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product(0L, "", 0, ""));
        return "products/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productservice.findById(id);
        model.addAttribute("product", product);
        return "products/form";
    }

    @PostMapping
    public String save (@RequestParam String name,
                       @RequestParam int price,
                       @RequestParam String imageUrl) {

        Product product = Product.builder()
                .name(name)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        productservice.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam int price,
                         @RequestParam String imageUrl) {

        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        productservice.update(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productservice.delete(id);
        return "redirect:/admin/products";
    }
}
*/
