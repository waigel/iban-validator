package com.waigel.backend.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.waigel.backend.validation.iban.IBANRegistry;
import com.waigel.backend.validation.iban.IBANRegistryLoader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IBANRegistryTest {

  private static IBANRegistry registry;

  @BeforeAll
  public static void setup() throws IOException {
    IBANRegistryLoader.loadRegistryFromFile();
    registry = new IBANRegistry();
  }

  @Test
  @DisplayName("Test if registry is loaded")
  public void testRegistryIsLoaded() {
    assertNotNull(IBANRegistryLoader.registry);
  }

  @Test
  @DisplayName("Test if registry contains 106 entries")
  public void testRegistryContains106Entries() {
    assertEquals(106, IBANRegistryLoader.registry.getIbans().size());
  }

  @Test
  @DisplayName("Test if registry contains DE")
  public void testRegistryContainsDE() {
    final var ibanCountry = registry.getCountryByCode("DE");
    assertEquals("DE", ibanCountry.getCountryCode());
    assertEquals("Germany", ibanCountry.getName());
    assertEquals(22, ibanCountry.getLength());

    assertNotNull(ibanCountry.getFlags());
    final var flags = ibanCountry.getFlags();
    assertTrue(flags.isInSwiftRegistry());
    assertTrue(flags.isSepaCountry());

    assertNotNull(ibanCountry.getEmbeds());
    final var embeds = ibanCountry.getEmbeds();
    assertNotNull(embeds.getBankCode());
    assertNull(embeds.getBranchCode());

    assertEquals(4, embeds.getBankCode().getPosition());
    assertEquals(8, embeds.getBankCode().getLength());
  }
}
