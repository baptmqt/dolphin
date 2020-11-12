package fr.bmqt.dolphin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@Getter
@AllArgsConstructor
public class KeyValue<K, V> {
    protected K key;
    protected V value;
}
