package com.dev.quickcart.order_service_api.api;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @PostMapping("/business")
    private String create(@RequestBody CustomerOrderRequestDto requestDto){
        customerOrderService.createOrder(requestDto);
    }

    @GetMapping("/visitors/find-by-id/{id}")
    private String findById(@PathVariable String id){
        customerOrderService.findOrderById(id);
    }


    @PutMapping("/business/update-status/{id}")
    private String updateOrder(@RequestBody CustomerOrderRequestDto requestDto,@PathVariable String id){
        customerOrderService.updateOrder(requestDto,id);
    }

    @PutMapping("/business/update-remark/{id}")
    private String manageRemark(@RequestParam String remark,@PathVariable String id){
        customerOrderService.manageRemark(remark,id);
    }

    @PutMapping("/business/update-status/{id}")
    private String manageStatus(@RequestParam String status,@PathVariable String id){
        customerOrderService.manageStatus(status,id);
    }

    @DeleteMapping("/business/delete-by-id/{id}")
    private String deleteById(@PathVariable String id){
        customerOrderService.deleteById(id);
    }


    @GetMapping("/visitors/search-all")
    private String searchAll(@RequestParam String searchText,@RequestParam int page,@RequestParam int size){
        customerOrderService.searchAll(searchText,page,size);
}
