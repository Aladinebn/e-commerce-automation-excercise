package Tests;

import Base.BasicTest;
import Pages.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests E2E — Catalogue Produits (/products)
 * Couvre : affichage liste, fiche détail, filtres catégorie (Women/Men/Kids), filtre brand.
 * Prérequis : BasicTest initialise le browser Playwright et expose `page`.
 */
public class ProductsTests extends BasicTest {

    private ProductPage productPage;

    // ── Setup ────────────────────────────────────────────────────────────────────

    /**
     * Instancie ProductPage et navigue vers /products avant chaque test.
     */
    @BeforeEach
    void setUpPage() {
        System.out.println("[SETUP] Initialisation de ProductPage");
        productPage = new ProductPage(page);
        productPage.navigateTo();
    }

    // ── Tests liste produits ─────────────────────────────────────────────────────

    /**
     * Vérifie que le catalogue affiche exactement 34 produits
     * et que chaque carte contient image, nom et prix.
     */
    @Test
    void productsList() {
        System.out.println("[TEST] productsList — vérification du nombre et des éléments visibles");

        int count = productPage.countProducts();
        System.out.println("[ASSERT] Nombre attendu : 34 | Obtenu : " + count);
        Assertions.assertEquals(34, count, "Le catalogue doit afficher 34 produits");

        System.out.println("[ASSERT] Vérification visibilité image / nom / prix du premier produit");
        Assertions.assertTrue(
                productPage.productImage.isVisible(),
                "L'image du produit doit être visible"
        );
        Assertions.assertTrue(
                productPage.productName.isVisible(),
                "Le nom du produit doit être visible"
        );
        Assertions.assertTrue(
                productPage.productPrice.isVisible(),
                "Le prix du produit doit être visible"
        );

        System.out.println("[PASS] productsList OK");
    }

    // ── Tests fiche détail produit ───────────────────────────────────────────────

    /**
     * Vérifie la fiche détail du produit 1 (Blue Top) :
     * image, nom, prix et disponibilité présents et corrects.
     */
    @Test
    public void productsCard() {
        System.out.println("[TEST] productsCard — clic sur 'View Product' du produit 1");
        productPage.clickBtn(productPage.viewProductBtn);

        System.out.println("[ASSERT] Vérification des éléments de la fiche produit");
        Assertions.assertTrue(productPage.cardProdImg.isVisible(),          "Image produit visible");
        Assertions.assertTrue(productPage.cardProdName.isVisible(),         "Nom produit visible");
        Assertions.assertTrue(productPage.cardProdPrice.isVisible(),        "Prix produit visible");
        Assertions.assertTrue(productPage.cardProdAvailability.isVisible(), "Disponibilité visible");

        String actualName = productPage.cardProdName.textContent();
        System.out.println("[ASSERT] Nom attendu : 'Blue Top' | Obtenu : '" + actualName + "'");
        Assertions.assertEquals("Blue Top", actualName, "Le nom du produit doit être 'Blue Top'");

        System.out.println("[PASS] productsCard OK");
    }

    // ── Tests filtres catégorie ──────────────────────────────────────────────────

    /**
     * Filtre Women > Dress → attend 3 produits.
     */
    @Test
    public void filterWomen() {
        System.out.println("[TEST] filterWomen — ouverture accordéon Women puis sélection Dress");
        productPage.clickBtn(productPage.womenCollapse);
        productPage.dressWomen.click();

        int count = productPage.countProducts();
        System.out.println("[ASSERT] Produits Women>Dress attendus : 3 | Obtenu : " + count);
        Assertions.assertEquals(3, count, "Filtre Women>Dress doit retourner 3 produits");

        System.out.println("[PASS] filterWomen OK");
    }

    /**
     * Filtre Men > Dress → attend 6 produits.
     */
    @Test
    public void filterMen() {
        System.out.println("[TEST] filterMen — ouverture accordéon Men puis sélection Dress");
        productPage.clickBtn(productPage.menCollapse);
        productPage.dressMen.click();

        int count = productPage.countProducts();
        System.out.println("[ASSERT] Produits Men>Dress attendus : 6 | Obtenu : " + count);
        Assertions.assertEquals(6, count, "Filtre Men>Dress doit retourner 6 produits");

        System.out.println("[PASS] filterMen OK");
    }

    /**
     * Filtre Kids > Dress → attend 6 produits.
     */
    @Test
    public void filterKids() {
        System.out.println("[TEST] filterKids — ouverture accordéon Kids puis sélection Dress");
        productPage.clickBtn(productPage.kidsCollapse);
        productPage.clickBtn(productPage.dressKids);

        int count = productPage.countProducts();
        System.out.println("[ASSERT] Produits Kids>Dress attendus : 6 | Obtenu : " + count);
        Assertions.assertEquals(6, count, "Filtre Kids>Dress doit retourner 6 produits");

        System.out.println("[PASS] filterKids OK");
    }

    // ── Tests filtre brand ───────────────────────────────────────────────────────

    /**
     * Filtre brand Polo → attend 6 produits.
     */
    @Test
    public void filterByBrand() {
        System.out.println("[TEST] filterByBrand — sélection brand 'Polo'");
        productPage.clickBtn(productPage.brandPolo);

        int count = productPage.countProducts();
        System.out.println("[ASSERT] Produits Polo attendus : 6 | Obtenu : " + count);
        Assertions.assertEquals(6, count, "Filtre brand Polo doit retourner 6 produits");

        System.out.println("[PASS] filterByBrand OK");
    }
}