package com.example.inventory_command_service.aggregates;

import api.CreateNewProductCommand;
import api.ProductCreatedEvent;
import api.ProduitStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String nom;
    private double prix;
    private int quntity;
    private ProduitStatus status;
    private String categorieID;

    public ProductAggregate() {
    }
    @CommandHandler
    public ProductAggregate(CreateNewProductCommand command) {
        log.info("Produit Creer");
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        log.info("Produuit event");
        this.productId=event.getId();
        this.nom = event.getPayload().getNom();
        this.prix = event.getPayload().getPrix();
        this.quntity = event.getPayload().getQuntity();
        this.status = event.getPayload().getStatus();
        this.categorieID = event.getPayload().getCatID();
    }




}
