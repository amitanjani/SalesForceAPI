package com.wavemaker.salesforce;

import java.util.List;

public class DiscoverSObject<T> {

    private DescribeSObjectBasic describeSObjectBasic;
    private List<T> recentItems;

    DiscoverSObject(DescribeSObjectBasic describeSObjectBasic, List<T> recentItems) {
        this.describeSObjectBasic = describeSObjectBasic;
        this.recentItems = recentItems;
    }

    public DescribeSObjectBasic getObjectDescribe() {
        return describeSObjectBasic;
    }

    public List<T> getRecentItems() {
        return recentItems;
    }
}
