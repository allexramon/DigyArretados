/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.semestre;

import birdpoint.professor.Professor;
import birdpoint.util.GenericDAO;

/**
 *
 * @author Nóbrega
 */
public class SemestreDAO extends GenericDAO<Semestre>{

    public SemestreDAO() {
        super(Semestre.class);
    }
    
}
