package com.tydic.softphone.entity;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Room {

    /* renamed from: id */
    private int f20022id;
    private Set<Publisher> publishers = new HashSet();

    public Room() {
    }

    public Room(int i) {
        this.f20022id = i;
    }

    public int getId() {
        return this.f20022id;
    }

    public void setId(int i) {
        this.f20022id = i;
    }

    public Set<Publisher> getPublishers() {
        return this.publishers;
    }

    public void addPublisher(Publisher publisher) {
        boolean z;
        Iterator<Publisher> it = this.publishers.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getId().equals(publisher.getId())) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        this.publishers.add(publisher);
    }

    public Publisher findPublisherById(BigInteger bigInteger) {
        for (Publisher publisher : this.publishers) {
            if (publisher.getId().equals(bigInteger)) {
                return publisher;
            }
        }
        return null;
    }

    public void removePublisherById(BigInteger bigInteger) {
        Iterator<Publisher> it = this.publishers.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(bigInteger)) {
                it.remove();
            }
        }
    }
}
