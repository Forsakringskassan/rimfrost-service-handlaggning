package se.fk.github.rimfrost.handlaggning.logic.entity;

import org.immutables.value.Value;

@Value.Immutable
public interface ErbjudandeFlowInfoEntity
{
   String erbjudandetyp();

   String bpmn();

   String namn();

   String beskrivning();

   String kafkaTopic();

   String kafkaRequestType();
}
