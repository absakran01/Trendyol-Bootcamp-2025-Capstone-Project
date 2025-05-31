    package com.absakran.capstone_project.repositories;

    import java.util.List;

import org.springframework.stereotype.Repository;

    import com.absakran.capstone_project.entities.Product;

    import jakarta.persistence.EntityManager;

import jakarta.transaction.Transactional;

    @Repository
    public class ProductDAOImpl implements ProductDAO{

        private EntityManager em;

        @Override
        public List<Product> getAllProducts() {
            // TODO Auto-generated method stub
            List<Product> result = null;
            try {
                result = em
                .createQuery("From Product", Product.class)
                .getResultList();
            } catch (Exception e) {
                throw e;
            }
            return result;
        }

        @Override
        @Transactional
        public void addProduct(Product product) {
            // TODO: process POST request
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null");
            }
            em.persist(product);
        }

        @Override
        public Product getProductById(long id) {
            // TODO Auto-generated method stub
            if(id==0){
                throw new IllegalArgumentException("Id cannot be null");
            }
            return em.find(Product.class, id);
        }

        @Override
        public List<Product> getProductsByKeyword(String keyword) {
            // TODO Auto-generated method stub
            if(keyword==null){
                throw new IllegalArgumentException("Keyword cannot be null");
            }
            return em.
            createQuery(
                    "FROM Product p WHERE LOWER(p.name) LIKE :kw OR LOWER(p.description) LIKE :kw",
                    Product.class
                )
                 .setParameter("kw", "%"+ keyword+ "%")
                 .getResultList();
        }

        @Override
        @Transactional
        public void updateProduct(Product product, long id) {
            // TODO Auto-generated method stub
            if(product==null){
                throw new IllegalArgumentException("Product cannot be null");
            }

            System.out.println(product.getQuantity());
            Product managedProduct = em.find(Product.class, id);
            managedProduct.setName(product.getName());
            managedProduct.setDescription(product.getDescription());
            managedProduct.setPrice(product.getPrice());
            managedProduct.setTax(product.getTax());
            System.out.println("updating product: " + managedProduct.getName() + " with quantity: " + product.getQuantity());
            managedProduct.setQuantity(product.getQuantity());
        }

        


        // DI 
        public ProductDAOImpl(EntityManager em){
            this.em = em;
            System.out.println("ProductDAOImpl constructor called");
        }

    }