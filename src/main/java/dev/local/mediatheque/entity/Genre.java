package dev.local.mediatheque.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre extends AbstractReference {

}
