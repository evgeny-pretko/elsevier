package com.mendeley.interview;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Catalogue class")
@Feature("Catalogue")
class CatalogueTest {

    Catalogue catalogue;

    @BeforeEach
    void setUp() {
        catalogue = new Catalogue();
    }


    //  1.	write a test to verify that the Catalog is empty on creation.
    @Test
    @Step @DisplayName("Catalog is empty on creation")
    void testCatalogIsEmptyOnCreation() {

        assertEquals(0, catalogue.size());
    }


    //  2.	add a Document and write a test to verify the Catalog is not empty.
    @Test
    @Step @DisplayName("Catalog is not empty after adding a document")
    void testCatalogIsNotEmptyAfterAddingDocuments() {
        Document document = new Document("Passport", 2016);
        catalogue.addDocument(document);

        assertEquals(1, catalogue.size());
    }


    //  3.	write a test to verify that retrieving documents returns the added documents.
    @Test
    @Step @DisplayName("Retrieving a document returns the added document")
    void testGetDocumentsByYearReturnsAddedDocuments() {
        Document document = new Document("Passport", 2016);
        catalogue.addDocument(document);

        List<Document> returnedDocuments = catalogue.getDocumentsByYear(2016);

        assertAll(
                () -> assertSame(document, returnedDocuments.get(0)),
                () -> assertEquals("Passport", returnedDocuments.get(0).getTitle()),
                () -> assertEquals(2016, returnedDocuments.get(0).getPublicationYear()),
                () -> assertEquals(1, returnedDocuments.size())
        );
    }


    //  4.	identify and write at least 2 other possible tests
    @Test
    @Step @DisplayName("Retrieving documents by year returns documents with provided year")
    void testGetDocumentsByYearReturnsOnlyDocumentsWithProvidedYear() {
        Document document1 = new Document("Passport", 2016);
        Document document2 = new Document("Visa", 2022);
        Document document3 = new Document("Driver License", 2022);
        catalogue.addDocument(document1);
        catalogue.addDocument(document2);
        catalogue.addDocument(document3);

        List<Document> returnedDocuments = catalogue.getDocumentsByYear(2022);

        assertAll(
                () -> assertFalse(returnedDocuments.contains(document1)),
                () -> assertTrue(returnedDocuments.containsAll(Arrays.asList(document2, document3)))
        );
    }

    @Test
    @Step @DisplayName("Retrieving documents by year returns empty list if there are no documents with provided year")
    void testGetDocumentsByYearReturnsEmptyListIfThereAreNoDocumentsWithProvidedYear() {
        Document document1 = new Document("Passport", 2016);
        Document document2 = new Document("Visa", 2022);
        catalogue.addDocument(document1);
        catalogue.addDocument(document2);

        List<Document> returnedDocuments = catalogue.getDocumentsByYear(2000);

        assertAll(
                () -> assertEquals(0, returnedDocuments.size()),
                () -> assertNotEquals(0, catalogue.size())
        );
    }

    @Test
    @Step @DisplayName("Sorting documents by year returns documents in right order")
    @Description("Create documents with different year values, sort by year in ascending order and verify that documents are sorted by year value.")
    void testGetSortedDocumentsReturnsDocumentsSorted() {
        Document document1 = new Document("Visa", 2022);
        Document document2 = new Document("Passport", 2016);
        Document document3 = new Document("Marriage Certificate", 2020);
        catalogue.addDocument(document1);
        catalogue.addDocument(document2);
        catalogue.addDocument(document3);

        List<Document> returnedDocuments = catalogue.getSortedDocuments(new DocumentsComparator());

        assertAll(
                () -> assertSame(document2, returnedDocuments.get(0)),
                () -> assertSame(document3, returnedDocuments.get(1)),
                () -> assertSame(document1, returnedDocuments.get(2))
        );
    }


    class DocumentsComparator implements Comparator<Document> {

        @Override
        public int compare(Document doc1, Document doc2) {
            return doc1.getPublicationYear() - doc2.getPublicationYear();
        }
    }

}