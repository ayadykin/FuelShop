var getConfig = function(gulp, gutil, name) {
    gutil.log('templatecache : ' + name);
    var templateCache = require('gulp-angular-templatecache');
    return gulp.src('js/' + name + '/templates/**/*.html').pipe(templateCache('templates.js', {
	module : 'shopApp'
    })).pipe(gulp.dest('../webapp/js/' + name + '/build/parts'));
};

module.exports = function(gulp, gutil) {

    return {
	adminViewer : function() {
	    getConfig(gulp, gutil, 'admin');
	},
	mainViewer : function() {
	    getConfig(gulp, gutil, 'shop');
	}
    };
};