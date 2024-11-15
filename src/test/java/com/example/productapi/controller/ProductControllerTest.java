package com.example.productapi.controller;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ProductService productServiceMock;

    @Test
    void getProductsByStatus_returns200_withMatchingProducts() throws Exception {
        ProductResponseDto productDto = new ProductResponseDto();
        List<ProductResponseDto> expectedProductDtos = List.of(productDto);

        given(productServiceMock.findByStatus(null)).willReturn(expectedProductDtos);

        MockHttpServletResponse response = mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        List<ProductResponseDto> actualProductDtos = objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {
        });
        assertThat(actualProductDtos).containsExactlyElementsOf(expectedProductDtos);
    }

    @Test
    void getSumOfValues_returns200_withSumComputedByService() throws Exception {
        Long expectedSum = 100L;

        given(productServiceMock.getSumOfValues(null, null)).willReturn(expectedSum);

        MockHttpServletResponse response = mockMvc.perform(get("/api/product/sum"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Long actualSum = Long.valueOf(response.getContentAsString());
        assertThat(expectedSum).isEqualTo(actualSum);
    }

    @Test
    void saveProduct_returns201_withProductDtoReturnedByService() throws Exception {
        NewProductRequestDto newProductDto = new NewProductRequestDto();
        newProductDto.setQuantity(10L);
        ProductResponseDto expectedProductResponseDto = new ProductResponseDto();
        expectedProductResponseDto.setQuantity(newProductDto.getQuantity());

        given(productServiceMock.save(newProductDto)).willReturn(expectedProductResponseDto);

        MockHttpServletResponse response = mockMvc.perform(post("/api/product")
                        .content(objectMapper.writeValueAsString(newProductDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        ProductResponseDto actualProductResponseDto =
                objectMapper.readValue(response.getContentAsString(), ProductResponseDto.class);
        assertThat(expectedProductResponseDto).isEqualTo(actualProductResponseDto);

        ArgumentCaptor<NewProductRequestDto> captor = ArgumentCaptor.forClass(NewProductRequestDto.class);
        then(productServiceMock).should().save(captor.capture());
        assertThat(newProductDto).isEqualTo(captor.getValue());
    }

    @Test
    void updateProduct_returns200_withProductReturnedByService() throws Exception {
        UpdatedProductRequestDto updatedProductDto = new UpdatedProductRequestDto();
        updatedProductDto.setProductId("abc");
        updatedProductDto.setQuantity(10L);
        ProductResponseDto expectedProductResponseDto = new ProductResponseDto();
        expectedProductResponseDto.setProductId(updatedProductDto.getProductId());
        expectedProductResponseDto.setQuantity(updatedProductDto.getQuantity());

        given(productServiceMock.update(updatedProductDto)).willReturn(expectedProductResponseDto);

        MockHttpServletResponse response = mockMvc.perform(put("/api/product")
                        .content(objectMapper.writeValueAsString(updatedProductDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        ProductResponseDto actualProductResponseDto =
                objectMapper.readValue(response.getContentAsString(), ProductResponseDto.class);
        assertThat(expectedProductResponseDto).isEqualTo(actualProductResponseDto);

        ArgumentCaptor<UpdatedProductRequestDto> captor = ArgumentCaptor.forClass(UpdatedProductRequestDto.class);
        then(productServiceMock).should().update(captor.capture());
        assertThat(updatedProductDto).isEqualTo(captor.getValue());
    }

    @Test
    void deleteProduct_invokesDeleteOnServiceWithPassedId_andReturns200() throws Exception {
        String productId = "567";

        willDoNothing().given(productServiceMock).deleteByProductId(productId);

        mockMvc.perform(delete("/api/product/" + productId))
                .andExpect(status().isOk());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(productServiceMock).should().deleteByProductId(captor.capture());
        assertThat(productId).isEqualTo(captor.getValue());
    }
}