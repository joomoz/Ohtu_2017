package statistics;

import java.util.ArrayList;
import java.util.List;
import statistics.matcher.Matcher;

/**
 *
 * @author jns
 */
class QueryBuilder {

    List<Matcher> matcher;

    public QueryBuilder() {
        this.matcher = new ArrayList<>();
    }

    public Matcher build() {
        List<Matcher> matchers = new ArrayList<>();
        matchers.addAll(matcher);
        matcher.clear();
        
        return new Matcher() {
            
            @Override
            public boolean matches(Player p) {
                return matchers.stream().allMatch(matcher1 -> matcher1.matches(p));
            }
            
        };
    }

    Object playsIn(String nyr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
