package Project.demo.Controller;

import Project.demo.Entity.Item;
import Project.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products/")
public class ItemController {
    private final ItemService itemService;
    //handle the api requests
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Item> addProduct(@RequestBody Item item) {
        itemService.addItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PatchMapping("/addProductQuantity/{id}/{quantity}")
    public ResponseEntity<String> addProductQuantity(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        itemService.AddProductQuantity(id, quantity);
        return new ResponseEntity<>("Successfully added " + id +" products by " + quantity + " products.", HttpStatus.OK);
    }
    @PatchMapping("/subtractProductQuantity/{id}/{quantity}")
    public ResponseEntity<String> subtractProductQuantity(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        itemService.SubtractProductQuantity(id, quantity);
        return new ResponseEntity<>("Successfully subtract the product quantity of id " + id + " by " + quantity, HttpStatus.OK);
    }
    @PatchMapping("/changePrice/{id}/{price}")
    public ResponseEntity<String> changePrice(@PathVariable("id") Long id, @PathVariable("price") BigDecimal price) {
        itemService.changePrice(id, price);
        return new ResponseEntity<>("Successfully changed to price " + price, HttpStatus.OK);
    }
    @PatchMapping("/buyProduct/{id}/{quantity}")
    public ResponseEntity<String> buyProduct(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        itemService.buyProduct(id, quantity);
        return new ResponseEntity<>("Successfully bought " + quantity + " " + id + " products.", HttpStatus.OK);
    }
    @PatchMapping("/returnProduct/{id}/{quantity}")
    public ResponseEntity<String> returnProduct(@PathVariable Long id, @PathVariable Long quantity) {
        itemService.returnProduct(id, quantity);
        return new ResponseEntity<>("Successfully returned " + quantity + " " + id + " products.", HttpStatus.OK);
    }
    @GetMapping("/getProductName/{id}")
    public ResponseEntity<String> getProductName(@PathVariable Long id) {
        String productName = itemService.getProductName(id);
        return new ResponseEntity<>("The product name is " + productName, HttpStatus.OK);
    }
    @GetMapping("/getProductPrice/{id}")
    public ResponseEntity<String> getProductPrice(@PathVariable("id") Long id) {
        BigDecimal price = itemService.getPrice(id);
        return new ResponseEntity<>("The price is " + price, HttpStatus.OK);
    }
    @GetMapping("/getProductQuantity/{id}")
    public ResponseEntity<String> getProductQuantity(@PathVariable("id") Long id) {
        Long quantity = itemService.getQuantity(id);
        return new ResponseEntity<>("The quantity is " + quantity, HttpStatus.OK);
    }
    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") Long id) {
        itemService.removeProduct(id);
        return new ResponseEntity<>(id + " successfully deleted", HttpStatus.OK);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Item>> getAllProducts() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        itemService.deleteAll();
        return new ResponseEntity<>("Items successfully deleted", HttpStatus.OK);
    }

}
