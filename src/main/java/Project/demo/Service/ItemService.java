package Project.demo.Service;

import Project.demo.Entity.Item;
import Project.demo.Exception.ItemIdDoesntExist;
import Project.demo.Exception.ItemIdExists;
import Project.demo.Exception.MoreThanAvaliable;
import Project.demo.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ItemService {
    //handle the item
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public void AddProductQuantity(Long id, Long quantity) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            itemRepository.findById(id).get().setQuantity(itemRepository.findById(id).get().getQuantity() + quantity);
            return;
        }
        throw new ItemIdDoesntExist(id + " doesn't exist!");
    }
    public void SubtractProductQuantity(Long id, Long quantity) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            if (itemRepository.findById(id).get().getQuantity() - quantity < 0) {
                throw new MoreThanAvaliable("Attempting to subtract more products than avaliable");
            }
            itemRepository.findById(id).get().setQuantity(itemRepository.findById(id).get().getQuantity() - quantity);
            return;
        }
        throw new ItemIdDoesntExist(id + " doesn't exist!");
    }
    public void changePrice(Long id, BigDecimal price) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            itemRepository.findById(id).get().setPrice(price);
            return;
        }
        throw new ItemIdDoesntExist(id + " doesn't exist!");
    }

    public void buyProduct(Long id, Long quantity) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            Long currentQuantity = itemRepository.findById(id).get().getQuantity();
            Long bought = itemRepository.findById(id).get().getBought();
            if (currentQuantity - quantity < 0) {
                throw new MoreThanAvaliable("You are buying more products than avaliable");
            }
            itemRepository.findById(id).get().setQuantity(currentQuantity - quantity);
            itemRepository.findById(id).get().setBought(bought + quantity);
            return;
        }
        throw new ItemIdDoesntExist(id + " doesn't exist!");
    }

    public void returnProduct(Long id, Long quantity) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            Long currentQuantity = itemRepository.findById(id).get().getQuantity();
            Long bought = itemRepository.findById(id).get().getBought();
            if (bought - quantity < 0) {
                throw new MoreThanAvaliable("You are returning more products than sold");
            }
            itemRepository.findById(id).get().setQuantity(currentQuantity + quantity);
            itemRepository.findById(id).get().setBought(bought - quantity);
            return;
        }
        throw new ItemIdDoesntExist(id + " doesn't exist!");
    }

    public void addItem(Item item) {
        if (!itemRepository.existsById(item.getId())) {
            System.out.println(item.getId());
            itemRepository.save(item);
            return;
        }
        throw new ItemIdExists(item.getId() + " exists!");
    }
    public String getProductName(Long id) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get().getProductName();
        }
        throw new ItemIdDoesntExist(id + " doesn't exist");
    }
    public BigDecimal getPrice(Long id) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get().getPrice();
        }
        throw new ItemIdDoesntExist(id + " doesn't exist");
    }
    public Long getQuantity(Long id) {
        if (itemRepository.existsById(id) && itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get().getQuantity();
        }
        throw new ItemIdDoesntExist(id + " doesn't exist");
    }
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    public void deleteAll() {
        itemRepository.deleteAll();
    }
    public void removeProduct(Long id) {;
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        }
        throw new ItemIdDoesntExist(id + "doesn't exist");
    }
}
