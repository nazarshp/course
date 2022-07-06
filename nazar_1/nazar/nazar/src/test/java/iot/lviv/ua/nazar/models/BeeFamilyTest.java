package iot.lviv.ua.nazar.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeeFamilyTest {

    private BeeFamily beeFamilyUnderTest;

    @BeforeEach
    void setUp() {
        beeFamilyUnderTest = new BeeFamily(0, 0);
    }

    @Test
    void testGetHeader() {
        assertThat(beeFamilyUnderTest.getHeader()).isEqualTo("beeFamilyId");
    }

    @Test
    void testToCSV() {
        assertThat(beeFamilyUnderTest.toCSV()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(beeFamilyUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(beeFamilyUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(beeFamilyUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(beeFamilyUnderTest.toString()).isEqualTo("result");
    }
}
