<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" href="images/favicon.html">
		<title>Kanaung Font Converter</title>
		<link href="template/css/bootstrap.css" rel="stylesheet">
		<link href="template/css/bootstrap-theme.css" rel="stylesheet">
		<link href="template/css/font.css" rel="stylesheet">
	</head>
	<body>
<?php
function find($a){
$filepath="./wordlist.dic";
$content = file_get_contents($filepath);
			$p = "/^".$a."(.*)/m";
		//	print($p);
			preg_match_all($p, $content, $found);
		//	print_r($found);			
		//	print("<br><br><br><br>");
			$words = array_unique($found[0]);
			$word_list = "";
			foreach($words as $word){
				$word_list .= $word."\n";
			}
			
			//print($word_list);
	
			//die();

$userdic_file = './'.$a.'.dic';

						/**
						 * @var $uaf 
						 */
						$uaf = fopen ( $userdic_file, 'w' ) or die ( "File is not writable or directory does not exist." );
						fwrite ( $uaf, $word_list );
						fclose ( $uaf );
			
}

$alphabets = array('က','ခ','ဂ','ဃ','င'
					,'စ','ဆ','ဇ','ဈ','ည'
					,'ဋ','ဌ','ဍ','ဎ','ဏ'
					,'တ','ထ','ဒ','ဓ','န'
					,'ပ','ဖ','ဗ','ဘ','မ'
					,'ယ','ရ','လ','ဝ','သ','ဟ','ဠ','အ','၁','၂','၃','၄','၅','၆','၇','၈','၉','၀');
foreach($alphabets as $a){
	find($a);
	}
$string = "ဖောက်-(ပေါက်အောင်~၊ပစ်~)";
//preg_match_all("/(.*)\((((.*?)(~))+)\)/U", $string, $match);
//print_r($match);
//$new_string = preg_replace("/(.*)\((((.*?)~)+)\)/","$2$1",$string);
//echo $new_string;

$input = "plain [indent] deep [indent] deeper [/indent] deep [/indent] plain";
function parseTagsRecursive($input)
{
    $regex = '#\[indent]((?:[^[]|\[(?!/?indent])|(?R))+)\[/indent]#';
    if (is_array($input)) {
        $input = '<div style="margin-left: 10px">'.$input[1].'</div>';
    }
    return preg_replace_callback($regex, 'parseTagsRecursive', $input);
}
$output = parseTagsRecursive($input);
//echo $output;
?>
</body>
</html>
