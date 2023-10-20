package practice.itemservice.web.basic;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.itemservice.domain.item.Item;
import practice.itemservice.domain.item.ItemRepository;

@RequiredArgsConstructor
@RequestMapping("/basic/items")
@Controller
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String getItems(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String getItem(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addFlashAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String getEditForm(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

}
