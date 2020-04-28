package Model.Requests;

import Model.Item;

public class ItemRequest  extends Request {
    Item newItem;

    public ItemRequest(String requestId, Item newItem) {
        super(requestId);
        this.newItem = newItem;
        String news="new Model.Item with Id:"+newItem.getId()+"is requested for being added in your System";
        this.setMessage(news);
    }

    public Item getNewItem() {
        return newItem;
    }
}