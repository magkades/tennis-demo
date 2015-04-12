package org.magkades.model;

import org.apache.commons.beanutils.BeanUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Contains the parameters to create new match.
 */
public class NewMatchParameters {

    private String player1;
    private String player2;

    public NewMatchParameters(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public NewMatchParameters(){}

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }


}
