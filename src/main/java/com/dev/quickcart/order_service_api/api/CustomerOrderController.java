package com.dev.quickcart.order_service_api.api;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.service.CustomerOrderService;
import com.dev.quickcart.order_service_api.utill.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @PostMapping("/business")
    private ResponseEntity<StandardResponseDto> create(@RequestBody CustomerOrderRequestDto requestDto) {
        customerOrderService.createOrder(requestDto);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        201, "Customer order has been created", null

                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/visitors/find-by-id/{id}")
    private ResponseEntity<StandardResponseDto> findById(@PathVariable String id) {
        customerOrderService.findOrderById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Customer order details", customerOrderService.findOrderById(id)

                ), HttpStatus.OK
        );
    }


    @PutMapping("/business/update-status/{id}")
    private ResponseEntity<StandardResponseDto> updateOrder(@RequestBody CustomerOrderRequestDto requestDto, @PathVariable String id) {
        customerOrderService.updateOrder(requestDto, id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Customer order has been created", null

                ), HttpStatus.OK
        );

    }

    @PutMapping("/business/update-remark/{id}")
    private ResponseEntity<StandardResponseDto> manageRemark(@RequestParam String remark, @PathVariable String id) {
        customerOrderService.manageRemark(remark, id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Customer order has been created", null

                ), HttpStatus.OK
        );
    }

    @PutMapping("/business/update-status/{id}")
    private ResponseEntity<StandardResponseDto> manageStatus(@RequestParam String status, @PathVariable String id) {
        customerOrderService.manageStatus(status, id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Customer order has been created", null

                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/business/delete-by-id/{id}")
    private ResponseEntity<StandardResponseDto> deleteById(@PathVariable String id) {
        customerOrderService.deleteById(id);
        return new ResponseEntity<>(
                new StandardResponseDto(
                        204, "Customer order has been deleted", null

                ), HttpStatus.NO_CONTENT
        );
    }


    @GetMapping("/visitors/search-all")
    private ResponseEntity<StandardResponseDto> searchAll(@RequestParam String searchText, @RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200, "Customer order details", customerOrderService.searchAll(searchText, page, size)

                ), HttpStatus.OK
        );
    }


}
