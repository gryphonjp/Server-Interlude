//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0-b170531.0717 
// Consulte <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2018.07.20 às 02:06:11 PM BRT 
//


package com.l2jbr.gameserver.model.entity.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de skillTargetType.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="skillTargetType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ONE"/&gt;
 *     &lt;enumeration value="SELF"/&gt;
 *     &lt;enumeration value="AREA"/&gt;
 *     &lt;enumeration value="MULTIFACE"/&gt;
 *     &lt;enumeration value="AURA"/&gt;
 *     &lt;enumeration value="UNLOCKABLE"/&gt;
 *     &lt;enumeration value="CORPSE_MOB"/&gt;
 *     &lt;enumeration value="UNDEAD"/&gt;
 *     &lt;enumeration value="AREA_UNDEAD"/&gt;
 *     &lt;enumeration value="AREA_CORPSE_MOB"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "skillTargetType")
@XmlEnum
public enum SkillTargetType {

    ONE,
    SELF,
    AREA,
    MULTIFACE,
    AURA,
    UNLOCKABLE,
    CORPSE_MOB,
    UNDEAD,
    AREA_UNDEAD,
    AREA_CORPSE_MOB;

    public String value() {
        return name();
    }

    public static SkillTargetType fromValue(String v) {
        return valueOf(v);
    }

}
