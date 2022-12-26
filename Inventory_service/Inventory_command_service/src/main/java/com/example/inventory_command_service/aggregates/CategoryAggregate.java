package com.example.inventory_command_service.aggregates;

import api.CategoryCreatedEvent;
import api.CreateNewCategoryCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class CategoryAggregate {
    @AggregateIdentifier
    private String catId;
    private String nom;
    private String description;


    public CategoryAggregate() {
    }
    @CommandHandler
    public CategoryAggregate(CreateNewCategoryCommand command) {
        log.info("Commande creer");
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CategoryCreatedEvent event){
        log.info("Categorie event");
        this.catId=event.getId();
        this.nom = event.getPayload().getNom();
        this.description = event.getPayload().getDescription();
    }
}
