import groovy.util.GroovyShellTestCase

class SparkyTest extends GroovyShellTestCase {

    def out = new ByteArrayOutputStream()

    void setUp() {
        super.setUp()
        System.setOut new PrintStream(out)
    }

    void tearDown() {
        super.tearDown()
    }

    def binding(variables) {
        withBinding(variables) {
            shell.evaluate(new File('sparky'))
        }
    }

    void "test it shows help with help argv"() {
        binding args: ['help']
        assert out.toString().trim().contains('USAGE')
    }

    void "test it graphs argv data"() {
        binding args: ['1,5,22,13,5']
        assert '▁▂█▅▂' == out.toString().trim()
    }

    void "test it charts pipe comma data"() {
        System.setIn new ByteArrayInputStream('0,30,55,80,33,150'.getBytes("UTF-8"))
        binding args: []
        assert '▁▂▃▄▂█' == out.toString().trim()
    }

    void "test it charts pipe spaced data"() {
        System.setIn new ByteArrayInputStream('0 30 55 80 33 150'.getBytes("UTF-8"))
        binding args: []
        assert '▁▂▃▄▂█' == out.toString().trim()
    }

    void "test it charts pipe newline data"() {
        System.setIn new ByteArrayInputStream('0\n30\n55\n80\n33\n150'.getBytes("UTF-8"))
        binding args: []
        assert '▁▂▃▄▂█' == out.toString().trim()
    }

    void "test it charts spaced data"() {
        binding args: ['0', '30', '55', '80', '33', '150']
        assert '▁▂▃▄▂█' == out.toString().trim()
    }

    void "test it charts way spaced data"() {
        binding args: ['0', '30', '              55', '80', '33', '    150']
        assert '▁▂▃▄▂█' == out.toString().trim()
    }

    void "test it handles decimals"() {
        binding args: "5.5,20".tokenize(',')
        assert '▁▇' == out.toString().trim()
    }

    void "test it charts 100 lt 300"() {
        binding args: "1,2,3,4,100,5,10,20,50,300".tokenize(',')
        assert '▁▁▁▁▃▁▁▁▂█' == out.toString().trim()
    }

    void "test it charts 50 lt 100"() {
        binding args: "1,50,100".tokenize(',')
        assert '▁▄█' == out.toString().trim()
    }

    void "test it charts 4 lt 8"() {
        binding args: "2,4,8".tokenize(',')
        assert '▁▃█' == out.toString().trim()
    }

    void "test it no tier 0"() {
        binding args: "1,2,3,4,5".tokenize(',')
        assert '▁▂▄▆█' == out.toString().trim()
    }

}
