package iot.lviv.ua.nazar.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TelemetryTest {

    private Telemetry telemetryUnderTest;

    @BeforeEach
    void setUp() {
        telemetryUnderTest = new Telemetry(0.0, 0.0, "street");
    }

    @Test
    void testGetHeader() {
        assertThat(telemetryUnderTest.getHeader()).isEqualTo("temperature, humidity, street");
    }

    @Test
    void testToCSV() {
        assertThat(telemetryUnderTest.toCSV()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(telemetryUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(telemetryUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(telemetryUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(telemetryUnderTest.toString()).isEqualTo("result");
    }
}
