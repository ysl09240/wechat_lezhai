package com.lezhai365.wechat.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.utils
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class XStreamUtil {

    protected static String PREFIX_CDATA = "<![CDATA[";
    protected static String SUFFIX_CDATA = "]]>";

    public static XStream init(boolean isAddCDATA) {
        XStream xstream = null;
        if (isAddCDATA) {
            xstream = new XStream(new XppDriver() {
                public HierarchicalStreamWriter createWriter(Writer out) {
                    return new PrettyPrintWriter(out) {
                        protected void writeText(QuickWriter writer, String text) {
                            if (!text.startsWith(PREFIX_CDATA)) {
                                text = PREFIX_CDATA + text + SUFFIX_CDATA;
                            }
                            writer.write(text);
                        }
                    };
                };
            });
        } else {
            xstream = new XStream();
        }
        return xstream;
    }
}
