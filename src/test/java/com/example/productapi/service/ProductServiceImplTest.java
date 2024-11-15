package com.example.productapi.service;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.entity.Product;
import com.example.productapi.data.entity.Status;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepositoryMock;

    @Mock
    ProductMapper productMapperMock;

    @Test
    void save_ifPassedDtoNotNull_returnsSavedProductAsDto() {
        NewProductRequestDto productDto = new NewProductRequestDto();
        Product product = new Product();
        Product savedProduct = new Product();
        ProductResponseDto expectedProductDto = new ProductResponseDto();
        savedProduct.setProductId("12345");
        expectedProductDto.setProductId(savedProduct.getProductId());

        given(productMapperMock.toProduct(productDto)).willReturn(product);
        given(productMapperMock.toResponseDto(savedProduct)).willReturn(expectedProductDto);
        given(productRepositoryMock.save(product)).willReturn(savedProduct);

        ProductService productService = new ProductServiceImpl(productRepositoryMock, productMapperMock);
        ProductResponseDto actualProductDto = productService.save(productDto);

        assertThat(actualProductDto).isNotNull();
        assertThat(actualProductDto).isEqualTo(expectedProductDto);
    }

    @Test
    void update_ifPassedDtoNotNull_returnsSavedProductAsDto() {
        UpdatedProductRequestDto productDto = new UpdatedProductRequestDto();
        productDto.setProductId("12345");
        productDto.setStatus(Status.INBOUND);
        Product product = new Product();
        Product updatedProduct = new Product();
        updatedProduct.setProductId(productDto.getProductId());
        updatedProduct.setStatus(productDto.getStatus());
        ProductResponseDto expectedProductDto = new ProductResponseDto();
        expectedProductDto.setProductId(updatedProduct.getProductId());
        expectedProductDto.setStatus(updatedProduct.getStatus());

        given(productMapperMock.toProduct(productDto)).willReturn(product);
        given(productMapperMock.toResponseDto(updatedProduct)).willReturn(expectedProductDto);
        given(productRepositoryMock.save(product)).willReturn(updatedProduct);

        ProductService productService = new ProductServiceImpl(productRepositoryMock, productMapperMock);
        ProductResponseDto actualProductDto = productService.update(productDto);

        assertThat(actualProductDto).isEqualTo(expectedProductDto);
    }

    @Test
    void deleteByProductId_forwardsToRepositoryWithPassedId() {
        ProductService productService = new ProductServiceImpl(productRepositoryMock, null);
        String actualProductId = "123";

        productService.deleteByProductId(actualProductId);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(productRepositoryMock).should().deleteByProductId(captor.capture());
        String expectedProductId = captor.getValue();
        assertThat(actualProductId).isEqualTo(expectedProductId);
    }

    @Test
    void findByStatus_forwardsToRepositoryWithPassedStatus_andReturnsListOfReturnedProductsAsDtos() {
        Product product = new Product();
        product.setStatus(Status.SELLABLE);
        List<Product> sellableProducts = List.of(product);
        given(productRepositoryMock.findByStatus(Status.SELLABLE)).willReturn(sellableProducts);
        ProductResponseDto expectedSellableProductDto = new ProductResponseDto();
        expectedSellableProductDto.setStatus(product.getStatus());
        given(productMapperMock.toResponseDto(product)).willReturn(expectedSellableProductDto);
        
        ProductService productService = new ProductServiceImpl(productRepositoryMock, productMapperMock);
        List<ProductResponseDto> actualProductDtos = productService.findByStatus(Status.SELLABLE);

        List<ProductResponseDto> expectedProductDtos = sellableProducts.stream()
                .map(productMapperMock::toResponseDto)
                .toList();
        assertThat(actualProductDtos).containsExactlyElementsOf(expectedProductDtos);
    }

    @Test
    void getSumOfValues_forwardsToRepositoryWithPassedValues() {
        Product product = new Product();
        product.setStatus(Status.INBOUND);
        product.setFulfillmentCenterId("1");
        product.setValue(3L);
        given(productRepositoryMock.getSumOfValues(product.getStatus(), product.getFulfillmentCenterId()))
                .willReturn(product.getValue());

        ProductService productService = new ProductServiceImpl(productRepositoryMock, productMapperMock);
        Long actualSum = productService.getSumOfValues(product.getStatus(), product.getFulfillmentCenterId());

        assertThat(actualSum).isEqualTo(product.getValue());
    }
}