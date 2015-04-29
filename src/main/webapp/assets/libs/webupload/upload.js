(function( $ ){
    // 优化retina, 在retina下这个值是2
      var ratio = window.devicePixelRatio || 1,
          fileCount = 0;
        // 缩略图大小
        thumbnailWidth = 110 * ratio,
        thumbnailHeight = 110 * ratio,

        // 判断浏览器是否支持图片的base64
        isSupportBase64 = ( function() {
            var data = new Image();
            var support = true;
            data.onload = data.onerror = function() {
                if( this.width != 1 || this.height != 1 ) {
                    support = false;
                }
            }
            data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
            return support;
        } )();

    var $queueList = $("#queueList"),
        $filePicker = $("#filePicker");

    var uploader = WebUploader.create({
        //auto: true,
        // 文件接收服务端。
        server: '/api/upload/file/img',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        disableGlobalDnd: true,
        fileNumLimit: 3,
        fileSizeLimit: 5 * 1024 * 1024,    // 5 M
        fileSingleSizeLimit: 5 * 1024 * 1024,    // 5 M
        compress: {
            width: 260,
            height: 180,

            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 90,

            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: false,

            // 是否允许裁剪。
            crop: false,

            // 是否保留头部meta信息。
            preserveHeaders: true,

            // 如果发现压缩后文件大小比原来还大，则使用原来图片
            // 此属性可能会影响图片自动纠正功能
            noCompressIfLarger: false,

            // 单位字节，如果图片大小小于此值，不会采用压缩。
            compressSize: 0
        }
    });
    uploader.on('startUpload', function () {
        //$('#upload-avatar').fadeOut(function () {
        //$('#upload-avatar').append('<img id="upload-loading" src="/assets/img/loading6.gif">');
        //});
    });

    uploader.on('error', function (code) {
        var text = "";
        switch (code){
            case 'Q_EXCEED_NUM_LIMIT':
                text = "最多可以上传3张"
                break;
            default :
                text = "上传出错,请重试";
                break;

        }
        //alert(text);
    });
    uploader.on('uploadSuccess', function (file, response) {

        console.log(response);
    });

    uploader.on( 'all', function( type, file ) {
        console.log("upload event type:" + type);
        switch( type ) {
            case 'fileQueued':
                fileCount++;
                if(fileCount === 3){
                    $filePicker.hide();
                }
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        alert( '不能预览' );
                        return;
                    }
                    if( isSupportBase64 ) {
                        img = $('<img id=' + file.id + ' class="queue-item" src="' + src + '">');
                        $queueList.prepend( img );
                    }
                }, thumbnailWidth, thumbnailHeight);
                break;
            case 'uploadFinished':
                console.log("上传结束");
                break;
            case 'startUpload':
                console.log("开始上传");
                break;
            case 'stopUpload':
                console.log("停止上传");
                break;

        }
    });

})( jQuery );