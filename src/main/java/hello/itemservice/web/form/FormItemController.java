package hello.itemservice.web.form;

import hello.itemservice.domain.formItem.DeliveryCode;
import hello.itemservice.domain.formItem.FormItem;
import hello.itemservice.domain.formItem.FormItemRepository;
import hello.itemservice.domain.formItem.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final FormItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL","서울");
        regions.put("BUSAN","부산");
        regions.put("JEJU","제주");
        return  regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypeps() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST","빠른배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL","일반배송"));
        deliveryCodes.add(new DeliveryCode("SLOW","느린배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<FormItem> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        FormItem item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "form/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new FormItem());
        return "form/addForm";

    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute FormItem item, RedirectAttributes redirectAttributes) {
        log.info("item.open = {}", item.getOpen());
        log.info("item.getRegions()={}",item.getRegions());
        FormItem savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        FormItem item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute FormItem item) {
        itemRepository.update(itemId, item);
        return "redirect:/form/items/{itemId}";
    }

}

