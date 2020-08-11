package com.pdm.shifter.dummy;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    public static final List<DummyItem> HISTORY = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 4;
    private static final int HISTORY_COUNT = 13;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }

        for (int i = 1; i <= HISTORY_COUNT; i++) {
            addHistoryItem(createHistoryDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static void addHistoryItem(DummyItem item) {
        HISTORY.add(item);
    }

    private static PunchType getPunchType(int position) {
        return position % 2 == 0 ? PunchType.OUT : PunchType.IN;
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(LocalDateTime.now(), getPunchType(position));
    }

    private static DummyItem createHistoryDummyItem(int position) {
        final LocalDateTime date = LocalDateTime.now();

        final DummyItem dummyItem = new DummyItem();
        dummyItem.setPunchType(getPunchType(position));

        final int remainder = (position + 1) % 4;
        if (remainder == 0) {
            date.plusDays(remainder);
            dummyItem.setDateItem(true);
        }

        dummyItem.setContent(date);

        return dummyItem;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        private String id = UUID.randomUUID().toString();
        private LocalDateTime content;
        private PunchType punchType;
        private boolean isDateItem = false;

        public DummyItem() {
        }

        public DummyItem(LocalDateTime content, PunchType punchType) {
            this.content = content;
            this.punchType = punchType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LocalDateTime getContent() {
            return content;
        }

        public void setContent(LocalDateTime content) {
            this.content = content;
        }

        public PunchType getPunchType() {
            return punchType;
        }

        public void setPunchType(PunchType punchType) {
            this.punchType = punchType;
        }

        public boolean isDateItem() {
            return isDateItem;
        }

        public void setDateItem(boolean dateItem) {
            isDateItem = dateItem;
        }

        @NonNull
        @Override
        public String toString() {
            return "DummyItem{" +
                    "id='" + id + '\'' +
                    ", content=" + content +
                    ", punchType=" + punchType +
                    ", isDateItem=" + isDateItem +
                    '}';
        }
    }
}