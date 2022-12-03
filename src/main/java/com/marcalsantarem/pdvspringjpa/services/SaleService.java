package com.marcalsantarem.pdvspringjpa.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.marcalsantarem.pdvspringjpa.entities.ItemSale;
import com.marcalsantarem.pdvspringjpa.entities.Product;
import com.marcalsantarem.pdvspringjpa.entities.Sale;
import com.marcalsantarem.pdvspringjpa.entities.User;
import com.marcalsantarem.pdvspringjpa.entities.dto.ProductDTO;
import com.marcalsantarem.pdvspringjpa.entities.dto.ProductInfoDTO;
import com.marcalsantarem.pdvspringjpa.entities.dto.SaleDTO;
import com.marcalsantarem.pdvspringjpa.entities.dto.SaleInfoDTO;
import com.marcalsantarem.pdvspringjpa.exceptions.InvalidOperationException;
import com.marcalsantarem.pdvspringjpa.exceptions.NoItemException;
import com.marcalsantarem.pdvspringjpa.repositories.ItemSaleRepository;
import com.marcalsantarem.pdvspringjpa.repositories.ProductRepository;
import com.marcalsantarem.pdvspringjpa.repositories.SaleRepository;
import com.marcalsantarem.pdvspringjpa.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	
	private final UserRepository userRepository;

	private final ProductRepository productRepository;

	private final SaleRepository saleRepository;

	private final ItemSaleRepository itemSaleRepository;
	
	public List<SaleInfoDTO> findAll(){
        return saleRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        return SaleInfoDTO.builder()
                .user(sale.getUser().getName())
                .date(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .products(getProductInfo(sale.getItems()))
                .build();
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {

        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items.stream().map(
                item -> ProductInfoDTO
                        .builder()
                        .id(item.getId())
                        .description(item.getProduct().getDescription())
                        .quantity(item.getQuantity()).build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public long save(SaleDTO sale){
        User user = userRepository.findById(sale.getUserid())
                .orElseThrow(() -> new NoItemException("Usuário não encontrado!"));

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);

        saveItemSale(items, newSale);

        return newSale.getId();
    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item: items){
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) {

        if(products.isEmpty()){
            throw new InvalidOperationException("Não possível adicionar a venda sem itens!");
        }

        return products.stream().map(item -> {
            Product product = productRepository.findById(item.getProductid())
                    .orElseThrow(()-> new NoItemException("Item da venda não encontrado"));

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            if(product.getQuantity() == 0){
                throw new NoItemException("Produto sem estoque.");
            } else if(product.getQuantity() < item.getQuantity()){
                throw new InvalidOperationException(
                        String.format("A quantidade de itens da venda (%s) " +
                                "é maior do que a quantidade disponível no estoque (%s", item.getQuantity(), product.getQuantity()));
            }

            int total = product.getQuantity() - item.getQuantity();
            product.setQuantity(total);
            productRepository.save(product);

            return itemSale;
        }).collect(Collectors.toList());
    }

    public SaleInfoDTO getById(long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(()-> new NoItemException("Venda não encontrada!"));

        return getSaleInfo(sale);
    }
	
}
