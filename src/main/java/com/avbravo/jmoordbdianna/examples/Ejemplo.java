/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.jmoordbdianna.examples;


import java.util.List;
import java.util.Optional;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.api.document.DocumentQuery;
import org.jnosql.diana.api.document.Documents;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

/**
 *
 * @author avbravo
 */
public class Ejemplo {

    public static final String DATABASE = "tallerdb";
    public static final String DOCUMENT_COLLECTION = "person";

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
    public void ejemplo(){
        // TODO code application logic here
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        try (DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
            DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

            DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));

            DocumentEntity entitySaved = collectionManager.save(entity);
            Optional<Document> id = entitySaved.find("_id");

            DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
            query.and(DocumentCondition.eq(id.get()));
            List<DocumentEntity> documentsFound = collectionManager.find(query);
            for (DocumentEntity de : documentsFound) {
                System.out.println(de.getDocuments());
                for (Document d : de.getDocuments()) {
                    System.out.println("d " + d.toString());

                }
            }

        }
        //catch(Exception ex){
//            System.out.println("error() "+ex.getLocalizedMessage());
//        }

    }

}
