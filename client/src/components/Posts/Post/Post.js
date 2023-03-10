import React from "react";

import useStyles from "./styles";
import {Card, CardActions, CardContent, CardMedia, Button, Typography} from '@material-ui/core';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import ThumbUpAltOutlined from '@material-ui/icons/ThumbUpAltOutlined';
import DeleteIcon from '@material-ui/icons/Delete';
import MoreHorizIcon from '@material-ui/icons/MoreHoriz';
import moment from 'moment';

import { useDispatch } from "react-redux";
import { deletePost, likePost } from "../../../actions/posts";


const Post = ({ post, setCurrentId }) => {
    const classes = useStyles();
    const dispatch = useDispatch();
    const user = JSON.parse(localStorage.getItem('profile'));

    // const Likes = () => {
    //     if (post.likes.length > 0) {
    //       return post.likes.find((like) => like === user?.user?.id)
    //         ? (
    //           <><ThumbUpAltIcon fontSize="small" />&nbsp;{post.likes.length > 2 ? `You and ${post.likes.length - 1} others` : `${post.likes.length} like${post.likes.length > 1 ? 's' : ''}` }</>
    //         ) : (
    //           <><ThumbUpAltOutlined fontSize="small" />&nbsp;{post.likes.length} {post.likes.length === 1 ? 'Like' : 'Likes'}</>
    //         );
    //     }
    
    //     return <><ThumbUpAltOutlined fontSize="small" />&nbsp;Like</>;
    //   };
    return(
        <Card className={classes.card}>
           
            <CardMedia className={classes.media} image={post.selectedFile} title={post.title}/>
            <div className={classes.overlay}> 
                <Typography variant="h6"> {post.name} </Typography>
                <Typography variant="body2"> {moment(post.createdAt).fromNow()} </Typography>
            </div>

            {
                // console.log(post?.creator)
                (user?.user?.id === post?.creator.id) && (
                    <div className={classes.overlay2}>
                        <Button 
                            style={{color: 'white'}} 
                            size="small" 
                            onClick={() =>  setCurrentId(post.id)}>
                            <MoreHorizIcon fontSize = "medium"/>
                        </Button>
                    </div>
                )
            }
            <div className={classes.details}>
                {/* <Typography variant="body2" color="inherit">{post.tags.map((tag)=>`#${tag} `)}</Typography> */}
                <Typography variant="body2" color="inherit">{post.tags}</Typography> 

            </div>
                <Typography className={ classes.title } variant="h5" gutterBottom> {post.title}</Typography>
            <CardContent>
                <Typography variant="h6" color="initial" component="p">{post.message}</Typography>  
            </CardContent>
            <CardActions>

                {/* <Button size="small" style={{color: 'white'}}  disabled={!user?.user} onClick={() => dispatch(likePost(post.id))}>
                    <Likes />

                </Button> */}
                {
                    (user?.user?.id === post?.creator.id) && (
                          <Button size="small" color='secondary'  onClick={() => dispatch(deletePost(post.id))}>
                                 <DeleteIcon fontSize="small"/>
                                 Delete   
                        </Button>
                    )
                }
              

            </CardActions>
            
        </Card>
    )
}

export default Post;