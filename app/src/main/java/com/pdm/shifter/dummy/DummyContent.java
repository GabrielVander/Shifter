package com.pdm.shifter.dummy;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PunchType getPunchType(int position) {
        return position % 2 == 0 ? PunchType.OUT : PunchType.IN;
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), Calendar.getInstance().getTime(), getPunchType(position));
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public Date content;
        public PunchType punchType;

        public DummyItem() {
        }

        public DummyItem(String id, Date content, PunchType punchType) {
            this.id = id;
            this.content = content;
            this.punchType = null;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getContent() {
            return content;
        }

        public void setContent(Date content) {
            this.content = content;
        }

        public PunchType getPunchType() {
            return punchType;
        }

        public void setPunchType(PunchType punchType) {
            this.punchType = punchType;
        }

        @NonNull
        @Override
        public String toString() {
            return "DummyItem{" +
                    "id='" + id + '\'' +
                    ", content=" + content +
                    ", punchType=" + punchType +
                    '}';
        }
    }
}