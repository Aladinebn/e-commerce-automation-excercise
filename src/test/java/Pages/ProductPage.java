package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object — /products
 * Couvre : liste produits, fiche détail, filtres catégorie, filtres brand, recherche.
 */
public class ProductPage {

    // ── Locators ────────────────────────────────────────────────────────────────

    /** Overlay de chaque carte produit — utilisé pour compter les produits affichés */
    public final Locator products;

    /** Bloc .productinfo du premier produit (image, prix, nom) */
    public final Locator productInfo;

    /** Image du premier produit dans la liste */
    public final Locator productImage;

    /** Prix du premier produit (balise h2 dans .productinfo) */
    public final Locator productPrice;

    /** Nom du premier produit (balise p dans .productinfo) */
    public final Locator productName;

    /** Bouton "View Product" du premier produit (product_details/1) */
    public final Locator viewProductBtn;

    // -- Fiche détail produit --

    /** Image principale sur la fiche produit */
    public final Locator cardProdImg;

    /** Prix sur la fiche produit (span imbriqué dans product-information) */
    public final Locator cardProdPrice;

    /** Nom du produit sur la fiche (h2 dans product-information) */
    public final Locator cardProdName;

    /** Indicateur de disponibilité ("Availability: In Stock") */
    public final Locator cardProdAvailability;

    // -- Filtres catégorie --

    /** Accordéon "WOMEN" dans la sidebar */
    public final Locator womenCollapse;

    /** Lien sous-catégorie Women > Dress (category_products/1) */
    public final Locator dressWomen;

    /** Accordéon "Men" dans la sidebar */
    public final Locator menCollapse;

    /** Lien sous-catégorie Men > Dress (category_products/3) */
    public final Locator dressMen;

    /** Accordéon "Kids" dans la sidebar (cible l'icône +) */
    public final Locator kidsCollapse;

    /** Lien sous-catégorie Kids > Dress (category_products/4) */
    public final Locator dressKids;

    // -- Filtres brand --

    /** Lien brand "Polo" dans la sidebar */
    public final Locator brandPolo;

    private final Page page;

    // ── Constructeur ────────────────────────────────────────────────────────────

    public ProductPage(Page page) {
        this.page = page;

        // Liste produits
        this.products    = page.locator("div.product-overlay");
        this.productInfo = page.locator(".productinfo").first();
        this.productImage = productInfo.locator("img");
        this.productPrice = productInfo.locator("h2");
        this.productName  = productInfo.locator("p");
        this.viewProductBtn = page.locator("a[href='/product_details/1']");

        // Fiche détail
        this.cardProdImg          = page.locator("div[class='view-product'] img[alt='ecommerce website products']");
        this.cardProdPrice        = page.locator("div[class='product-information'] span span");
        this.cardProdName         = page.locator("div[class='product-information'] h2");
        this.cardProdAvailability = page.getByText("Availability: In Stock");

        // Filtres catégorie
        this.womenCollapse = page.locator("a:has-text('WOMEN')");
        this.dressWomen    = page.locator("a[href='/category_products/1']");
        this.menCollapse   = page.locator("//a[normalize-space()='Men']");
        this.dressMen      = page.locator("a[href='/category_products/3']");
        this.kidsCollapse  = page.locator("//a[normalize-space()='Kids']//i[@class='fa fa-plus']");
        this.dressKids     = page.locator("a[href='/category_products/4']");

        // Filtres brand
        this.brandPolo = page.locator("//a[@href='/brand_products/Polo']");
    }

    // ── Méthodes ────────────────────────────────────────────────────────────────

    /**
     * Navigue vers la page catalogue produits.
     */
    public void navigateTo() {
        String url = "https://automationexercise.com/products";
        System.out.println("[NAV] Navigation vers : " + url);
        page.navigate(url);
        System.out.println("[NAV] Page chargée — titre : " + page.title());
    }

    /**
     * Retourne le nombre de produits actuellement affichés dans le catalogue.
     *
     * @return nombre de cartes produit visibles
     */
    public int countProducts() {
        int count = products.count();
        System.out.println("[PRODUCTS] Nombre de produits affichés : " + count);
        return count;
    }

    /**
     * Clique sur un locator donné et loggue l'action.
     *
     * @param locator élément cible
     */
    public void clickBtn(Locator locator) {
        System.out.println("[CLICK] Clic sur : " + locator);
        locator.click();
    }
}