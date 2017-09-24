package io.dehasi.core;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import javax.annotation.Nonnull;

/** Represents key-value property. */
public class Property implements Comparable<Property>{
    final String name;
    final String value;

    Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("value", value)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property that = (Property) o;

        return Objects.equal(this.name, that.name) && Objects.equal(this.value, that.value);
    }

    @Override
    public int compareTo(@Nonnull Property that) {
        return ComparisonChain.start().compare(this.name, that.name).result();
    }
}
